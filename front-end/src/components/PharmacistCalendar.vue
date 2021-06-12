<template>
  <div>
    <v-row class="fill-height">
      <v-col>
        <v-sheet height="64">
          <v-toolbar flat>
            <v-btn
              outlined
              class="mr-4"
              color="grey darken-2"
              @click="setToday"
            >
              Today
            </v-btn>
            <v-btn fab text small color="grey darken-2" @click="prev">
              <v-icon small> mdi-chevron-left </v-icon>
            </v-btn>
            <v-btn fab text small color="grey darken-2" @click="next">
              <v-icon small> mdi-chevron-right </v-icon>
            </v-btn>
            <v-toolbar-title v-if="$refs.calendar">
              {{ $refs.calendar.title }}
            </v-toolbar-title>
            <v-spacer></v-spacer>
            <v-menu bottom right>
              <template v-slot:activator="{ on, attrs }">
                <v-btn outlined color="grey darken-2" v-bind="attrs" v-on="on">
                  <span>{{ typeToLabel[type] }}</span>
                  <v-icon right> mdi-menu-down </v-icon>
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
            <v-card color="grey lighten-4" min-width="350px" flat>
              <v-toolbar :color="selectedEvent.color" dark>
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
                Price: <ins v-html="selectedEvent.price"></ins> <br /><br />
                Patient: <ins v-html="selectedEvent.patient"></ins> <br /><br />
                Address: <ins v-html="selectedEvent.address"></ins>
                <span v-html="selectedEvent.details"></span>
              </v-card-text>
              <v-dialog v-if="checkApp(selectedEvent.start)" v-model="dialog" persistent max-width="700px">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn color="orange" style="margin: 10px;" dark v-bind="attrs" v-on="on" @click="onDialogClick(selectedEvent.id)">
                    Start Appointment
                  </v-btn>
                </template>
                <AppointmentForm @clicked="dialog=false;selectedOpen=false;"/>
              </v-dialog>
              <v-btn color="orange" style="margin: 10px;" dark @click="didntCome(selectedEvent.id)">
                Didn't come
              </v-btn>
            </v-card>
          </v-menu>
        </v-sheet>
      </v-col>
    </v-row>
  </div>
</template>


<script>
import axios from "axios";
import AppointmentForm from '@/components/AppointmentForm.vue';
import TokenDecoder from '../services/token-decoder';
export default {
  components: { AppointmentForm },
  name: "PharmacistCalendar",
  data: () => ({
    focus: "",
    type: "month",
    dialog: false,
    typeToLabel: {
      month: "Month",
      week: "Week",
      day: "Day",
      "4day": "4 Days",
    },
    selectedEvent: {},
    selectedElement: null,
    selectedOpen: false,
    events: [],
    appointments: [],
  }),
  created() {
    var user = TokenDecoder.getUserEmail();
    let path =
      `http://localhost:9090/api/pharmacist/appointments/${user}`;

    axios.get(path).then((response) => {
      this.appointments = response.data;
      const events = [];

      for (let i = 0; i < this.appointments.length; i++) {
        const first = this.appointments[i].startTime;
        const second = this.appointments[i].endTime;
        var startDate = new Date(first);
        var now = new Date();
        var cancel = false;
        startDate.setDate(startDate.getDate() - 1);
        if (startDate > now) cancel = true;
        events.push({
          name: "Appointment",
          start: first,
          price: this.appointments[i].price,
          patient:
            this.appointments[i].patient.name +
            " " +
            this.appointments[i].patient.surname,
          address:
            this.appointments[i].address.street +
            " " +
            this.appointments[i].address.streetNumber +
            ", " +
            this.appointments[i].address.place,
          end: second,
          color: "orange",
          timed: false,
          cancel: cancel,
          id: this.appointments[i].id,
        });
      }
      this.events = events;
    });
    axios.get(`http://localhost:9090/api/pharmacist/getPharmacy/${user}`).then(response=>localStorage.setItem('selectedPh',response.data));
  },
  mounted() {
    this.$refs.calendar.checkChange();
  },
  methods: {
    viewDay({ date }) {
      this.focus = date;
      this.type = "day";
    },
    getEventColor(event) {
      return event.color;
    },
    setToday() {
      this.focus = "";
    },
    prev() {
      this.$refs.calendar.prev();
    },
    next() {
      this.$refs.calendar.next();
    },
    showEvent({ nativeEvent, event }) {
      const open = () => {
        this.selectedEvent = event;
        this.selectedElement = nativeEvent.target;
        setTimeout(() => {
          this.selectedOpen = true;
        }, 10);
      };

      if (this.selectedOpen) {
        this.selectedOpen = false;
        setTimeout(open, 10);
      } else {
        open();
      }

      nativeEvent.stopPropagation();
    },
    updateRange() {},
    rnd(a, b) {
      return Math.floor((b - a + 1) * Math.random()) + a;
    },
    start(id) {
      console.log(id);
    },
    onDialogClick(id){
      for(var i =0;i<this.appointments.length;i++){
        if(this.appointments[i].id==id){
          localStorage.setItem('patient',this.appointments[i].patient.email);
          break;
        }
      }
    },
    didntCome(id){
      //treba odraditi penale
      alert("Penal"+id);
      window.location.reload();
    },
    checkApp(time){
      var a = new Date(time);
      var b = new Date()
      const diffTime = Math.abs(a - b);
      const diffMinutes = Math.ceil(diffTime / (1000 * 60));
      if(diffMinutes<=15){
        return true;
      }
      return false;
    }
  },
};
</script>