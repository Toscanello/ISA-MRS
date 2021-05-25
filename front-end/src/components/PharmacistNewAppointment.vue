<template>
  <v-container>
    <v-row >
      <v-col id="date-pickr">
        <v-date-picker
          v-model="date"
          :min="new Date().toISOString().slice(0, 10)"
        ></v-date-picker>
      </v-col>
      <v-col id="time-pickr">
        <v-time-picker v-model="time" format="24hrs"></v-time-picker>
      </v-col>
    </v-row>

    <v-row id="price">
      <v-text-field
        v-model="price"
        label="Price of appointment"
        value="1000"
        filled
      ></v-text-field>
    </v-row>

    <v-row justify="center">
      <v-btn @click="scheduleAppointment">Schedule</v-btn>
    </v-row>
  </v-container>
</template>

<script>
import TokenDecoder from "@/services/token-decoder";
import axios from "axios";
export default {
  name: "PharmacistNewAppointment",
  data() {
    return {
      time: "",
      date: "",
      mins: 30,
      price: 1000,
      status: "",
    };
  },
  computed: {
    timeFromMins() {
      if (this.mins == 60) return "01:00:00";
      else return "00:" + this.mins + ":00";
    },
  },
  methods: {
    scheduleAppointment() {
      let user_role = TokenDecoder.getUserRole();
      let appointment = {
        begin: this.date + " " + this.time,
        duration: this.timeFromMins,
        pharmacyRegNo: "abc",
        dermatologistEmail: TokenDecoder.getUserEmail(),
        price: this.price,
        patientEmail: localStorage.getItem('patient'),
        id: 0,
      };
      let path = "";
      if (user_role == "ROLE_PHARMACIST")
        path = "http://localhost:9090/api/pharmacist/addAppointment";
      else
        path =
          "http://localhost:9090/api/dermatologists/scheduleNewAppointmentByTime";
      axios
        .post(path, appointment)
        .then((response) => {
          alert(response.data);
          this.$emit("clicked", "calendar");
        })
        .catch((response) => {
          this.status = response.data;
          alert(status);
          this.$emit("clicked", "patients");
        });
    },
  },
};
</script>

<style>
 #date-pickr {
   margin: 1px;
 }

 #time-pickr {
   margin: 1px;
 } 
</style>