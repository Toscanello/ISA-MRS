<template>
  <div>
  <v-row class="fill-height">
    <v-col>
      <v-sheet height="64">
        <v-toolbar
          flat
        >
          <v-btn
            outlined
            class="mr-4"
            color="grey darken-2"
            @click="setToday"
          >
            Today
          </v-btn>
          <v-btn
            fab
            text
            small
            color="grey darken-2"
            @click="prev"
          >
            <v-icon small>
              mdi-chevron-left
            </v-icon>
          </v-btn>
          <v-btn
            fab
            text
            small
            color="grey darken-2"
            @click="next"
          >
            <v-icon small>
              mdi-chevron-right
            </v-icon>
          </v-btn>
          <v-toolbar-title v-if="$refs.calendar">
            {{ $refs.calendar.title }}
          </v-toolbar-title>
          <v-spacer></v-spacer>
          <v-menu
            bottom
            right
          >
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                outlined
                color="grey darken-2"
                v-bind="attrs"
                v-on="on"
              >
                <span>{{ typeToLabel[type] }}</span>
                <v-icon right>
                  mdi-menu-down
                </v-icon>
              </v-btn>
            </template>
            <v-list>
              <v-list-item @click="type = 'day'">
                <v-list-item-title>Day</v-list-item-title>
              </v-list-item>
              <v-list-item @click="type = 'week'">
                <v-list-item-title>Week</v-list-item-title>
              </v-list-item>
              <v-list-item @click="type = 'month'">
                <v-list-item-title>Month</v-list-item-title>
              </v-list-item>
              <v-list-item @click="type = '4day'">
                <v-list-item-title>4 days</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </v-toolbar>
      </v-sheet>
      <v-sheet height="600">
        <v-calendar
          ref="calendar"
          v-model="focus"
          color="primary"
          :events="events"
          :event-color="getEventColor"
          :type="type"
          @click:event="showEvent"
          @click:more="viewDay"
          @click:date="viewDay"
          @change="updateRange"
        ></v-calendar>
        <v-menu
          v-model="selectedOpen"
          :close-on-content-click="false"
          :activator="selectedElement"
          offset-x
        >
          <v-card
            color="grey lighten-4"
            min-width="350px"
            flat
          >
            <v-toolbar
              :color="selectedEvent.color"
              dark
            >
              <v-btn icon>
                <v-icon>mdi-pencil</v-icon>
              </v-btn>
              <v-toolbar-title v-html="selectedEvent.name"></v-toolbar-title>
              <v-spacer></v-spacer>
              <v-btn icon>
                <v-icon>mdi-heart</v-icon>
              </v-btn>
              <v-btn icon>
                <v-icon>mdi-dots-vertical</v-icon>
              </v-btn>
            </v-toolbar>
            <v-card-text>
              Price: <ins v-html="selectedEvent.price"></ins>
              <br><br>
              Doctor: <ins v-html="selectedEvent.medicalWorker"></ins>
              <br><br>
              Adderss: <ins v-html="selectedEvent.address"></ins>
              <span v-html="selectedEvent.details"></span>
            </v-card-text>
            <v-card-actions>
              <v-btn v-if="selectedEvent.cancel"
                text
                color="secondary" 
                @click ="cacncelEvent"
                @change="updateRange"
              >
                Cancel
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-menu>
      </v-sheet>
    </v-col>
  </v-row>
  </div>
</template>


<script>
import axios from 'axios'
import TokenDecoder from '../services/token-decoder'
import authHeader from '../services/auth-header';
  export default {
      name: 'Calendar',
    data: () => ({
      focus: '',
      type: 'month',
      typeToLabel: {
        month: 'Month',
        week: 'Week',
        day: 'Day',
        '4day': '4 Days',
      },
      selectedEvent: {},
      selectedElement: null,
      selectedOpen: false,
      events: [],
      appointments: []
    }),
     created() {
      let path = "http://localhost:9090/patients/appointments/" + TokenDecoder.getUserEmail();
      
      axios.get(path, { headers: authHeader() })
      .then(response => {
        this.appointments = response.data
        const events = []

        for (let i = 0; i < this.appointments.length; i++) {

          const first = this.appointments[i].startTime
          const second = this.appointments[i].endTime
          var startDate = new Date(first)
          var now = new Date()
          var cancel = false
          startDate.setDate(startDate.getDate()-1)
          if(startDate > now)
            cancel = true
          events.push({
            name: 'Appointment',
            start: first,
            price: this.appointments[i].price,
            medicalWorker: this.appointments[i].medicalWorker.name + " " +
                           this.appointments[i].medicalWorker.surname,
            address: this.appointments[i].address.street + " " + 
                     this.appointments[i].address.streetNumber
                      + ", " + this.appointments[i].address.place,
            end: second,
            color: 'orange',
            timed: false,
            cancel: cancel,
            id: this.appointments[i].id,
          })
        }
        this.events = events
        })
      
  },
    mounted () {

      this.$refs.calendar.checkChange()
    },
    methods: {
      cacncelEvent(){
        this.selectedOpen = false
        console.log(this.selectedEvent.id)
        axios
        .post('http://localhost:9090/patients/cancelAppointment/' , {id : this.selectedEvent.id}, { headers: authHeader() })
        window.location.reload()
      },
      viewDay ({ date }) {
        this.focus = date
        this.type = 'day'
      },
      getEventColor (event) {
        return event.color
      },
      setToday () {
        this.focus = ''
      },
      prev () {
        this.$refs.calendar.prev()
      },
      next () {
        this.$refs.calendar.next()
      },
      showEvent ({ nativeEvent, event }) {
        const open = () => {
          this.selectedEvent = event
          this.selectedElement = nativeEvent.target
          setTimeout(() => {
            this.selectedOpen = true
          }, 10)
        }

        if (this.selectedOpen) {
          this.selectedOpen = false
          setTimeout(open, 10)
        } else {
          open()
        }

        nativeEvent.stopPropagation()
      },
      updateRange () {
        
      },
      rnd (a, b) {
        return Math.floor((b - a + 1) * Math.random()) + a
      },
    },
  }
</script>