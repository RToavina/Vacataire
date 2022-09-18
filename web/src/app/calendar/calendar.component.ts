import { Component, OnInit } from '@angular/core';
import { CalendarOptions, defineFullCalendarElement } from '@fullcalendar/web-component';
import dayGridPlugin from '@fullcalendar/daygrid';
import frLocale from '@fullcalendar/core/locales/fr';
import interactionPlugin from '@fullcalendar/interaction';

// make the <full-calendar> element globally available by calling this function at the top-level
defineFullCalendarElement();

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  calendarOptions: CalendarOptions = {
    locale:'fr',
    plugins: [dayGridPlugin, interactionPlugin ],
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,dayGridWeek,dayGridDay'
    },
    buttonText:{
      today:    "Aujourd'hui",
      month:    'Mois',
      week:     'Semaine',
      day:      'Jour',
      list:     'Liste'
    },
    firstDay:1,
    hiddenDays:[0],
    dateClick: this.emarger,
    eventDisplay:'auto',
    events: [
      {
        title  : 'event1',
        start  : '2022-09-23'
      },
      {
        title  : 'event2',
        start  : '2022-09-25',
        end    : '2022-09-27'
      },
      {
        title  : 'event3',
        start  : '2022-09-29',
        allDay : false // will make the time show
      }
    ]

  };

  emarger(info:any){
    //TODO Mettre le corps
  }

}
