<template>
  <div>
    <pharmacy-admin-home>
    </pharmacy-admin-home>
    <v-row justify="center">
      <v-col
        cols="12"
        sm="10"
        md="8"
        lg="6"
      >
        <v-card class="overflow-hidden">
          <v-app-bar 
            color="dark cyan"
            dark
            prominent
            flat>
            <v-toolbar-title>Zakazivanje slobodnog termina</v-toolbar-title>
          </v-app-bar>
          <v-spacer></v-spacer>
          <v-container>
            <div id="pickers">
              <v-row justify="center">
                <div id="date-pickr">
                  <v-date-picker v-model="date"
                  color="dark cyan"></v-date-picker>
                </div>
                <div id="time-pickr">
                  <v-time-picker v-model="time" format="24hrs"
                  color="dark cyan"></v-time-picker>
                </div>
              </v-row>
            </div>
            <br />
            <hr />
            <br />
            <div id="minutes">
              <v-subheader>Trajanje pregleda: {{mins}}min</v-subheader>
              <v-slider
                  v-model="mins"
                  step="15"
                  min="15"
                  max="60"
                  thumb-label
                  ticks
                ></v-slider>
            </div>
            <div id="price">
              <v-text-field
                  ref="price"
                  v-model="price"
                  :rules="[() => !!price || 'Polje je obavezno']"
                  label="Unesite cenu pregleda"
                  value="1000"
                  filled
              ></v-text-field>
            </div>
            <v-btn class="submit-dgm" @click="scheduleAppointment">Zakazi</v-btn>
          </v-container>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import axios from 'axios'
import PharmacyAdminHome from './PharmacyAdminHome.vue'
import authHeader from '../services/auth-header'
export default {
    components: { PharmacyAdminHome },
    name: 'DermatologistAppointmentForm',
    data () {
        return {
            time: '',
            date: '',
            mins: 0,
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
              pharmacyRegNo: this.$route.params.regNo,
              dermatologistEmail: this.$route.params.email,
              price: this.price
            }
            console.log(appointment)
            if (!this.price || this.price != Number(this.price)) {
              alert('Niste dobro uneli cenu')
              return 
            }
            axios
            .post('http://localhost:9090/api/dermatologists/addAppointment',
                     appointment, { headers: authHeader() })
            .then(response => {
              console.log(response.data)
              alert(response.data)
            })
            .catch(response => {
              console.log(response)
              console.log(response.data)
              this.status = response.data
              alert(status)
            })
        }
    }
}
</script>

<style>
 /*.v-date-picker-title {
     color: darkcyan;
 }

 .v-time-picker-title {
     background-color: darkcyan;
     padding: 10px;
 }*/

 #date-pickr {
   margin: 15px;
   display: inline;
 }

 #time-pickr {
   display: inline;
   margin: 15px;
 }

 #minutes {
     width: 50%;
     padding: 20px;
     margin: 15px;
 }

 .submit-dgm {
   margin: 10px;
 }
</style>