<template>

  <div
  >
    <v-row class= "row">
      <v-col v-for="pharmacy in pharmacies" :key="pharmacy.regNo" cols="12" sm="6" md="4" lg="3">

        <v-card
        class="pharmacy-card"
        >
        <v-img class = "card-img"
        src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
        ></v-img>

        <v-card-title>
        {{pharmacy.name}}
        </v-card-title>

        <v-card-subtitle>
        {{pharmacy.address.street}} {{pharmacy.address.streetNumber}}, {{pharmacy.address.place}}, {{pharmacy.address.country}}
        </v-card-subtitle>

        <v-card-actions>
        <v-btn class="open-btn"
        text
        v-on:click="open()"
        >
          Open
        </v-btn>

        <v-spacer></v-spacer>

        <v-btn
        icon
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
