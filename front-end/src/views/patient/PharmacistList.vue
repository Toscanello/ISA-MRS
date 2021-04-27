<template>

  <div
  >
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
    <v-row class= "row">
    <v-col v-for="pharmacist in pharmacists" :key="pharmacist" cols="12" sm="6" md="4" lg="3">
      <v-card
            color="#385F73"
            dark
            style="margin-top: 5px; margin-left: 5px;"
          >
            <v-card-title class="text-h5">
              Farmaceut Farmacetovic
            </v-card-title>

            <v-card-subtitle>Kontakt {{pharmacist}}</v-card-subtitle>

            <v-card-actions>
              <v-btn text v-on:click="open()">
                Add appointment
              </v-btn>
            </v-card-actions>
          </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    name: "PharmacistList",
    data () {
      return {
        pharmacists : [],
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
    mounted() {
      let path = "http://localhost:9090/api/pharmacy/pharmacist/2021-01-01_10:00_abc"
      axios.get(path)
      .then(response => {
        this.pharmacists = response.data
        })
      .catch(error => {
        alert('Error: status ' + error.response.status)
        this.$router.push('/')
      })
    
    }
  }
</script>