<template>

  <div
  >
    <v-row class= "row">
    <v-col v-for="pharmacist in pharmacists" :key="pharmacist" cols="12" sm="6" md="4" lg="3">

      <v-card
        class="pharmacy-card"
      >
        <v-img class = "card-img"
        src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
        ></v-img>

        <v-card-title>
        {{pharmacist}}
        </v-card-title>

        <v-card-subtitle>
        Ovdje ce biti ispisani neki dodatni podaci
        </v-card-subtitle>

        <v-card-actions>
          <v-btn class="open-btn"
            text
          >
            Open
          </v-btn>

          <v-spacer></v-spacer>

          <v-btn
            icon
            @click="show = !show"
          >
            <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
          </v-btn>
        </v-card-actions>

        <v-expand-transition>
          <div v-show="show">
            <v-divider></v-divider>
            <v-card-text>
              Radno vrijeme
            </v-card-text>
          </div>
        </v-expand-transition>
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
        pharmacists : []
      }
    },
    mounted() {
      let path = "http://localhost:9090/api/pharmacy/pharmacist/2021-01-01_08:00_abc"
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