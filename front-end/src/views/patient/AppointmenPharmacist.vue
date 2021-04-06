<template>
<v-app>
    <div style="background-color:darkcyan;">
  <v-row no-gutters>
    <v-col
      cols="12"
      lg="4"
    >
      <v-menu
        ref="menu"
        v-model="menu2"
        :close-on-content-click="false"
        :nudge-right="40"
        :return-value.sync="time"
        transition="scale-transition"
        offset-y
        max-width="50%"
        min-width="290px"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-text-field
            v-model="time"
            label="Picker in menu"
            prepend-icon="mdi-clock-time-four-outline"
            readonly
            v-bind="attrs"
            v-on="on"
            color="blue-grey darken-1"
          ></v-text-field>
        </template>
        <v-time-picker
          color="blue-grey darken-1"
          v-model="time"
          full-width
          @click:hour="$refs.menu.save(time)"
          @click:minute="$refs.menu.save(time)"
        ></v-time-picker>
      </v-menu>
    </v-col>
    <v-spacer></v-spacer>
    <v-col
      cols="12"
      lg="4"
    >
    <v-menu
        v-model="menu2"
        :close-on-content-click="false"
        :nudge-right="40"
        transition="scale-transition"
        offset-y
        min-width="auto"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-text-field
            v-model="date"
            label="Picker without buttons"
            prepend-icon="mdi-calendar"
            readonly
            v-bind="attrs"
            v-on="on"
          ></v-text-field>
        </template>
        <v-date-picker
          v-model="date"
          @input="menu2 = false"
        ></v-date-picker>
      </v-menu>
    </v-col>
    <v-col
      cols="6"
      sm="2"
      >
      <v-btn
        v-on:click="greet"
        label="Picker without buttons"
        large
        depressed 
        :value="false"
      >
      Search
      </v-btn>
    </v-col>
  </v-row>
  </div>
  <div v-if="renderPharmaciesList"  class="listOfPharmacies">
       <Pharmacies :pharmacies="pharmacies"/>
  </div>
  </v-app>
</template>


<script>
  import Pharmacies from '../patient/Pharmacies'
  import axios from 'axios'
  
  export default {
    name: 'AppointmenPharmacist',
    components: {
      Pharmacies
  },

    data () {
      return {
        time: '09:05',
        menu2: false,
        menu: false,
        modal2: false,
        date: new Date().toISOString().substr(0, 10),
        date_menu: false,
        modal: false,
        pharmacies : null,
        renderPharmaciesList: true
      }
    },
    methods: {
    greet: function () {
        let path = "http://localhost:9090/api/pharmacy/apoteke/" + this.date + "_" + this.time
        
        axios
        .get(path)
        .then(response => {
          this.pharmacies = response.data
          })
        .catch(error => {
          alert('Error: status ' + error.response.status)
          this.$router.push('/')
        })
        console.log(JSON.stringify(this.pharmacies))
      this.renderPharmaciesList = false
      this.$nextTick(()=>{
          this.renderPharmaciesList = true;
      })
    }
  },
    
    mounted() {
      
        let path = "http://localhost:9090/api/pharmacy/apoteke/" + this.date + "_" + this.time
        axios
        .get(path)
        .then(response => {
          this.pharmacies = response.data
          })
        .catch(error => {
          alert('Error: status ' + error.response.status)
          this.$router.push('/')
        })
      
    }
  
    
  }
</script>
