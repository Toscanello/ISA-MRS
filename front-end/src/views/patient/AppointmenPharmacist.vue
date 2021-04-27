<template>
<div>
  <div>
    <v-toolbar style="background-color:darkcyan;">
    <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>
    <v-toolbar-title>Title</v-toolbar-title>
    </v-toolbar>

    <v-navigation-drawer
      v-model="drawer"
      app
      temporary
    >
      <v-list
        nav
        dense
      >
        <v-list-item-group
          v-model="group"
        >
          <template v-for="item in items">
            <v-list-item :key="item.title" :to="item.to">
              <v-list-item-icon>
                <v-icon>{{ item.icon }}</v-icon>
              </v-list-item-icon>
              <v-list-item-title >{{ item.title }}</v-list-item-title>
            </v-list-item>
          </template>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
  </div>
  <div style="background-color:blue-grey;">
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
            label="Start time"
            prepend-icon="mdi-clock-time-four-outline"
            readonly
            v-bind="attrs"
            v-on="on"
            color="blue-grey lighten-5"
          ></v-text-field>
        </template>
        <v-time-picker
          
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
            label="Start date"
            prepend-icon="mdi-calendar"
            readonly
            v-bind="attrs"
            v-on="on"
            color="blue-grey lighten-5"
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
        style="margin-top: 10px;"
      >
      Search
      </v-btn>
    </v-col>
  </v-row>
  </div>
  <div v-if="renderPharmaciesList"  class="listOfPharmacies">
       <Pharmacies :pharmacies="pharmacies"/>
  </div>
</div>
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
        renderPharmaciesList: true,
         drawer: false,
      group: null,
      items: [
      {
        icon: "mdi-home",
        title: "Home",
        to: "/pharmacies-list",
      },
      {
        icon: "mdi-account",
        title: "Acoount",
        to: "/",
      },
      {
        icon: "mdi-calendar",
        title: "Calendar",
        to: "/calendar",
      },
      {
        icon: "mdi-hospital-box",
        title: "Drugs",
        to: "/",
      },
      {
        icon: "mdi-prescription",
        title: "Prescription",
        to: "/",
      },
      {
        icon: "mdi-plus",
        title: "Appointment",
        to: "/appointment-pharmacist",
      },
      {
        icon: "mdi-plus",
        title: "Allegries",
        to: "/",
      },
      {
        icon: "mdi-emoticon-angry-outline",
        title: "Complaints",
        to: "/",
      },
    ],
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
