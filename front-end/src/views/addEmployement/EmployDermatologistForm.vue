<template>
    <div>
    <pharmacy-admin-home></pharmacy-admin-home>
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
            <v-toolbar-title>Employ dermatologist</v-toolbar-title>
          </v-app-bar>
          <v-spacer></v-spacer>
          <v-container>
            <div id="pickers">
              <v-row justify="center">
                <div id="start-time-pickr">
                  <v-time-picker v-model="startTime"
                  color="dark cyan"></v-time-picker>
                </div>
                <div id="end-time-pickr">
                  <v-time-picker v-model="endTime"
                  color="dark cyan"></v-time-picker>
                </div>
              </v-row>
            </div>
            <v-btn class="submit-dgm" @click="employ">Employ</v-btn>
          </v-container>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import axios from 'axios'
import PharmacyAdminHome from '../PharmacyAdminHome.vue'
import authHeader from '../../services/auth-header'
export default {
  components: { PharmacyAdminHome },
    Name: 'EmployDermatologistForm',
    data() {
        return {
            startTime: '',
            endTime: '',
        }
    },
    methods: {
        employ() {
            let workHour = {
                workerEmail: this.$route.params.email,
                pharmacyRegNo: this.$route.params.regNo,
                startTime: this.startTime,
                endTime: this.endTime
            }
            axios
            .post(
              'http://localhost:9090/api/dermatologists/employ', workHour, 
              { headers: authHeader() }
            )
            .then(response => {
                console.log(response)
                alert('Successfully added an employee')
                this.$router.push('/employ/list/dermatologists/' + this.$route.params.regNo)
            })
            .catch(error => {
                alert(error.response.data)
            })
        }
    }
}
</script>

<style>
 #start-time-pickr {
   margin: 15px;
   display: inline;
 }

 #end-time-pickr {
   display: inline;
   margin: 15px;
 }

 .submit-dgm {
   margin: 10px; 
 }
</style>