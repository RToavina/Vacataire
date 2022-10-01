import {Component, OnInit, ViewChild} from '@angular/core';
import {CalendarOptions, defineFullCalendarElement} from '@fullcalendar/web-component';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import timeGridPlugin from '@fullcalendar/timegrid'
import {MatDialog} from "@angular/material/dialog";
import {EmargementComponent} from "../emargement/emargement.component";
import {AuthService} from "../services/auth.service";
import {ProfesseurService} from "../services/professeur.service";
import {iif, mergeMap, of, Subject} from "rxjs";
import {Professeur} from "../model/professeur";
import {Calendar} from "@fullcalendar/core";
import {Emargement} from "../model/emargement";
import {EmargementService} from "../services/emargement.service";

// make the <full-calendar> element globally available by calling this function at the top-level
defineFullCalendarElement();

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit {
  professeur: Professeur;
  reload = new Subject<void>();

  @ViewChild('calendar') calendar: Calendar;

  constructor(private authService: AuthService,
              private professeurService: ProfesseurService,
              private dialog: MatDialog,
              private emargementService: EmargementService) {
  }

  ngOnInit(): void {
    this.init();
    this.reload.subscribe(() => this.init());
  }

  init() {
    this.authService.getUserConnected().pipe(mergeMap(user =>
      iif(() =>
          user != null,
        this.professeurService.getProfesseur(user?.username),
        of({})
      ))).subscribe(p => {
        if(p != null) {
          this.professeur = new Professeur(p);
          this.initCalendar();
        }
      }
    );
  }

  initCalendar() {
    const emargements = this.professeur?.emargements?.map(e => {
      return {
        id: e.id.toString(),
        title: e.matiere.nomMatiere,
        start: new Date(e.date + 'T' + e.debut),
        end: new Date(e.date + 'T' + e.fin),
        allDay: false
      }
    })
    this.calendarOptions = {
      ...this.calendarOptions,
      events: emargements
    };
  }

  calendarOptions: CalendarOptions = {
    locale: 'fr',
    plugins: [dayGridPlugin, timeGridPlugin, interactionPlugin],
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay',
    },
    buttonText: {
      today: "Aujourd'hui",
      month: 'Mois',
      week: 'Semaine',
      day: 'Jour',
      list: 'Liste'
    },
    firstDay: 1,
    eventDisplay: 'auto',
    height: "auto",
    slotMinTime: "06:00",
    slotMaxTime: "18:00",
    allDaySlot: false,
    businessHours: {
      // days of week. an array of zero-based day of week integers (0=Sunday)
      daysOfWeek: [1, 2, 3, 4, 5], // Monday - Thursday
      startTime: '06:00', // a start time (10am in this example)
      endTime: '20:00', // an end time (6pm in this example)
    },

    eventClick: (info) => this.emarger(info)
  };

  emarger(info: any) {
    const emargement = this.professeur?.emargements?.find(x => x.id == info.event.id);
    this.openDialog(emargement);
  }


  openDialog(emargement?: Emargement): void {
    const diaglogRef = this.dialog.open(EmargementComponent, {
      width: '800px',
      data: {
        professeur: this.professeur,
        emargement: emargement
      }
    });

    diaglogRef.afterClosed().subscribe(result => {
      if (result != null) {
        const emargementRequest = {
          username: this.professeur.username,
          date: result.date,
          debut: result.debut,
          fin: result.fin,
          done: false,
          id: result.id,
          matiere: result.matiere.nomMatiere
        };

        this.emargementService.emarger(emargementRequest).subscribe(res => {
          this.reload.next();
        })
      }
    });
  }
}
