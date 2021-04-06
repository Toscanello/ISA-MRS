<template>
  <div>
    <div id="pickers">
      <v-date-picker v-model="date"></v-date-picker>
      <v-time-picker v-model="time" format="24hrs"></v-time-picker>
    </div>
    <br />
    <hr />
    <br />
    <div id="minutes">
      <v-subheader>Trajanje pregleda: {{mins}}min</v-subheader>
      <v-slider
          v-model="mins"
          step="15"
          min="0"
          max="60"
          thumb-label
          ticks
        ></v-slider>
    </div>
    <div id="price">
      <v-text-field
          v-model="price"
          label="Unesite cenu pregleda"
          value="1000"
          filled
      ></v-text-field>
    </div>
    <v-btn @click="scheduleAppointment">Zakazi</v-btn>
  </div>
</template>

<script>
import axios from 'axios'
export default {
    name: 'DermatologistAppointmentForm',
    data () {
        return {
            time: '',
            date: '',
            mins: 0,
            price: 1000,
        }
    },
    computed: {
      timeFromMins () {
        if (mins == 60)
          return '01:00:00'
        else
          return '00:'+mins+':00'
      }
    },
    methods: {
        scheduleAppointment() {
            alert(this.time + '\n' + this.date + '\n' 
            + this.mins + '\n' + this.price)
            axios
            .post('/api/dermatologists/addAppointment', {
              begin: this.date + ' ' + this.time,
              duration: this.timeFromMins,
              pharmacyRegNo: this.$route.params.regNo,
              dermatologistEmail: this.$route.params.email,
              price: this.price
            })
            .then(response => {
              alert(response.data)
            })
            .catch(response => {
              alert(response.data)
            })
        }
    }
}
</script>

<style>
 .v-date-picker-title {
     color: darkcyan;
 }

 .v-time-picker-title {
     background-color: darkcyan;
     padding: 10px;
 }

 #minutes {
     width: 50%;
     padding: 20px;
     margin: 15px;
 }
</style>