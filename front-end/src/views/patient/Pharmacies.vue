<template>

  <div
  >
    <home-page-patient></home-page-patient>
    <v-row class= "row">
      <v-col v-for="pharmacy in pharmacies" :key="pharmacy.regNo" cols="12" sm="6" md="4" lg="3">
        
          <v-card
            color="#385F73"
            dark
            style="margin-top: 5px; margin-left: 5px;"
          >
            <v-card-title class="text-h5">
              {{pharmacy.name}}
            </v-card-title>

            <v-card-subtitle>{{pharmacy.address.street}} {{pharmacy.address.streetNumber}}, {{pharmacy.address.place}}, {{pharmacy.address.country}}</v-card-subtitle>

            <v-card-actions>
              <v-btn text v-on:click="open()">
                PHARMACIST
              </v-btn>
            </v-card-actions>
          </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
  export default {
    pharmacies: "Pharmacies",
    props: {
        pharmacies : Array
    },
    methods: {
    open: function () {
      this.$router.push('/pharmacist-list');
    },
  },
  }
</script>
