<template>
  <div>
    <v-row class="d-flex justify-end">
      <v-col>
        <v-menu bottom right>
          <template v-slot:activator="{ on, attrs }">
            <v-btn outlined color="grey darken-2" v-bind="attrs" v-on="on">
              <span>{{ selected.name }}</span>
              <v-icon right> mdi-menu-down </v-icon>
            </v-btn>
          </template>
          <v-list v-for="item in items" :key="item.regNo">
            <v-list-item @click="onChange(item)">
              <v-list-item-title>{{ item.name }}</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
      </v-col>
    </v-row>
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
            :key="componentKey"
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
                <h3>
                  Price: <ins v-html="selectedEvent.price"></ins> <br /><br />
                </h3>
                <h3 v-if="selectedEvent.patient != ''">
                  Patient: <ins v-html="selectedEvent.patient"></ins>
                  <br /><br />
                </h3>
                <h3 v-if="selectedEvent.address != ''">
                  Address: <ins v-html="selectedEvent.address"></ins>
                </h3>
                <span v-html="selectedEvent.details"></span>
              </v-card-text>
              <v-dialog
                v-if="checkApp(selectedEvent.start) && !selectedOpen.selected && !selectedEvent.finished"
                v-model="dialog"
                persistent
                max-width="700px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    color="orange"
                    style="margin: 10px"
                    dark
                    v-bind="attrs"
                    v-on="on"
                    @click="onDialogClick(selectedEvent.id)"
                  >
                    Start Appointment
                  </v-btn>
                </template>
                <AppointmentForm :patient=selectedEvent.patient_email
                  @clicked="onFinish(selectedEvent.id)"
                />
              </v-dialog>
              <v-btn color="orange" style="margin: 10px;" dark @click="didntCome(selectedEvent.id)"
              v-if="checkApp(selectedEvent.start) && !selectedOpen.selected && !selectedEvent.finished">
                Didn't come
              </v-btn>
              <v-card-actions>
                <v-btn
                  v-if="selectedEvent.schedule && checkPatient()"
                  text
                  color="secondary"
                  @click="scheduleNewAppointment"
                  @change="updateRange"
                >
                  Schedule
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
import TokenDecoder from "@/services/token-decoder";
import AppointmentForm from "@/components/AppointmentForm.vue";
import axios from "axios";
import authHeader from '../services/auth-header';
export default {
  components: { AppointmentForm },
  name: "DermatologistCalendar",
  data: () => ({
    items: [],
    selected: "",
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
    dermappointments: [],
    componentKey: 0,
  }),
  created() {
    let user = TokenDecoder.getUserEmail();
    axios
      .get(`http://localhost:9090/api/pharmacy/getAll/${user}`, {headers: authHeader()})
      .then((response) => {
        this.items = response.data;
        this.selected = this.items[0];
        localStorage.setItem("selectedPh", this.selected.regNo);
        let path =
          `http://localhost:9090/api/dermatologists/appointments/${user}/${this.selected.regNo}`;
        let path2 =
          `http://localhost:9090/api/dermatologists/dermAppointments/${user}/${this.selected.regNo}`;

        axios.get(path, {headers: authHeader()}).then((response) => {
          this.appointments = response.data;

          for (let i = 0; i < this.appointments.length; i++) {
            const first = this.appointments[i].startTime;
            const second = this.appointments[i].endTime;
            this.events.push({
              name: "Appointment",
              start: first,
              price: this.appointments[i].price,
              patient:
                this.appointments[i].patient.name +
                " " +
                this.appointments[i].patient.surname,
              patient_email:
                this.appointments[i].patient.email,
              address:
                this.appointments[i].address.street +
                " " +
                this.appointments[i].address.streetNumber +
                ", " +
                this.appointments[i].address.place,
              end: second,
              color: "orange",
              timed: false,
              schedule: false,
              finished: this.appointments[i].finished,
              id: this.appointments[i].id,
            });
          }
        });
        axios.get(path2, {headers: authHeader()}).then((response) => {
          this.dermappointments = response.data;

          for (let i = 0; i < this.dermappointments.length; i++) {
            var first = new Date(this.dermappointments[i].begin);
            first.setTime(first.getTime() + 2 * 60 * 60 * 1000);
            var diff = this.dermappointments[i].duration.substring(3, 5);
            if (this.dermappointments[i].duration[1] == "1") diff = "60";
            var newDateObj = new Date(first.getTime() + parseInt(diff) * 60000);

            this.events.push({
              name: "FreeAppointment",
              start: first.toISOString().substring(0, 19),
              price: this.dermappointments[i].price,
              patient: "",
              patient_email:"",
              address: "",
              end: newDateObj.toISOString().substring(0, 19),
              color: "yellow",
              timed: false,
              schedule: true,
              finished: true,
              id: this.dermappointments[i].id,
            });
          }
        });
      });

    localStorage.setItem("pacijent", "");
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
    scheduleNewAppointment() {
      this.selectedOpen = false;
      let email = localStorage.getItem("pacijent");
      axios
        .post(
          `http://localhost:9090/api/dermatologists/scheduleNewAppointment/${this.selectedEvent.id}/${email}`,null ,{headers: authHeader()}
        )
        .then((response) => {
          alert(response.data);
          localStorage.setItem("pacijent", "");
          window.location.reload();
        });
    },
    onChange(item) {
      this.selected = item;
      localStorage.setItem("selectedPh", item.regNo);
      let path = `http://localhost:9090/api/dermatologists/appointments/${TokenDecoder.getUserEmail()}/${
        item.regNo
      }`;
      let path2 = `http://localhost:9090/api/dermatologists/dermAppointments/${TokenDecoder.getUserEmail()}/${
        item.regNo
      }`;
      this.selectedEvent = {};
      this.selectedElement = null;
      this.selectedOpen = false;
      this.events = [];
      axios.get(path, {headers: authHeader()}).then((response) => {
        this.appointments = response.data;

        for (let i = 0; i < this.appointments.length; i++) {
          const first = this.appointments[i].startTime;
          const second = this.appointments[i].endTime;
          this.events.push({
            name: "Appointment",
            start: first,
            price: this.appointments[i].price,
            patient:
              this.appointments[i].patient.name +
              " " +
              this.appointments[i].patient.surname,
            patient_email:
              this.appointments[i].patient.email,
            address:
              this.appointments[i].address.street +
              " " +
              this.appointments[i].address.streetNumber +
              ", " +
              this.appointments[i].address.place,
            end: second,
            color: "orange",
            timed: false,
            schedule: false,
            finished: this.appointments[i].finished,
            id: this.appointments[i].id,
          });
        }
      });
      axios.get(path2, {headers: authHeader()}).then((response) => {
        this.dermappointments = response.data;

        for (let i = 0; i < this.dermappointments.length; i++) {
          var first = new Date(this.dermappointments[i].begin);
          first.setTime(first.getTime() + 2 * 60 * 60 * 1000);
          var diff = this.dermappointments[i].duration.substring(3, 5);
          if (this.dermappointments[i].duration[1] == "1") diff = "60";
          var newDateObj = new Date(first.getTime() + parseInt(diff) * 60000);

          this.events.push({
            name: "FreeAppointment",
            start: first.toISOString().substring(0, 19),
            price: this.dermappointments[i].price,
            patient: "",
            patient_email:"",
            address: "",
            end: newDateObj.toISOString().substring(0, 19),
            color: "yellow",
            timed: false,
            schedule: true,
            finished: true,
            id: this.dermappointments[i].id,
          });
        }
      });
    },
    onDialogClick(id) {
      for (var i = 0; i < this.appointments.length; i++) {
        if (this.appointments[i].id == id) {
          localStorage.setItem("patient", this.appointments[i].patient.email);
          break;
        }
      }
    },
    checkApp(time) {
      var a = new Date(time);
      var b = new Date();
      const diffTime = Math.abs(a - b);
      const diffMinutes = Math.ceil(diffTime / (1000 * 60));
      if (diffMinutes <= 15) {
        return true;
      }
      return false;
    },
    checkPatient() {
      return localStorage.getItem("pacijent") != "";
    },
    onFinish(id){
      this.dialog = false;
      this.selectedOpen = false;
      let check  = localStorage.getItem('check_finished');
      if(check=="true"){
        axios.post(`http://localhost:9090/api/appointment/finished/${id}/${true}`,null, {headers: authHeader()});
      }
      
    },
    didntCome(id){
      //treba odraditi penale
      alert("Penal"+id);
      axios.put(`http://localhost:9090/patients/penalty/${id}`,null, {headers: authHeader()});
      axios.post(`http://localhost:9090/api/appointment/finished/${id}/${false}`,null, {headers: authHeader()});
      window.location.reload();
    }
  },
};
</script>