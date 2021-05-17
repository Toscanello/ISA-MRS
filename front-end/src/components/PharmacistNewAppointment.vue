<template>
  <div>
    <div id="pickers">
      <div id="date-pickr">
        <v-date-picker v-model="date" :min="new Date().toISOString().slice(0,10)"></v-date-picker>
      </div>
      <div id="time-pickr">
        <v-time-picker v-model="time" format="24hrs"></v-time-picker>
      </div>
    </div>
    <br />
    <hr />
    <br />
    <div id="price">
      <v-text-field
          v-model="price"
          label="Price of appointment"
          value="1000"
          filled
      ></v-text-field>
    </div>
    <v-btn @click="scheduleAppointment">Schedule</v-btn>
  </div>
</template>

<script>
import axios from 'axios'
export default {
    name: 'PharmacistNewAppointment',
    data () {
        return {
            time: '',
            date: '',
            mins: 30,
            price: 1000,
            status: ''
        }
    },
    computed: {
      timeFromMins () {
        if (this.mins == 60)
          return '01:00:00'
        else
          return '00:'+this.mins+':00'
      }
    },
    methods: {
        scheduleAppointment() {
            let appointment = {
              begin: this.date + ' ' + this.time,
              duration: this.timeFromMins,
              pharmacyRegNo: 'abc',
              dermatologistEmail: "farm@gmail.com",
              price: this.price,
              patientEmail:'mika95455@gmail.com',
              id:0
            }
            console.log(appointment)
            axios
            .post('http://localhost:9090/api/pharmacist/addAppointment', appointment)
            .then(response => {
              console.log(response.data)
              alert(response.data)
              this.$emit("clicked", 'calendar');
            })
            .catch(response => {
              console.log(response)
              console.log(response.data)
              this.status = response.data
              alert(status)
              this.$emit("clicked", 'patients');
            })
        }
    }
}
</script>

<style>

 #date-pickr {
   margin: 15px;
   display: inline;
 }

 #time-pickr {
   display: inline;
   margin: 15px;
 }
</style>