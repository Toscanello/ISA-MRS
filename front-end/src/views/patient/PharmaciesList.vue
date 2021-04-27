<template>
  <div
  >     
  <home-page-patient></home-page-patient>
  <v-toolbar
          dark
          class="mb-1"
        >
          <v-text-field
            v-model="search"
            clearable
            flat
            solo-inverted
            hide-details
            prepend-inner-icon="mdi-magnify"
            label="Search"
            style="padding-right: 50px;"
            
          ></v-text-field>
          
          <v-select
              v-model="sortBy"
              flat
              solo-inverted
              hide-details
              prepend-inner-icon="mdi-magnify"
              label="Sort by"
            ></v-select>
            
            <v-btn
                large
                depressed
                :value="false"
              >
                <v-icon>mdi-arrow-up</v-icon>
              </v-btn>
              <v-btn
                large
                depressed
                :value="true"
              >
                <v-icon>mdi-arrow-down</v-icon>
              </v-btn>
          </v-toolbar>
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
import HomePagePatient from '../HomePagePatient.vue';
export default {
  components: { HomePagePatient },
    name: 'PharmacyList',
    data: () => ({
    drawer: false,
    group: null,
    pharmacies: [],
  }),
  created() {
    axios.get("http://localhost:9090/api/pharmacy/all").then((resp) => {
      this.pharmacies = resp.data;
    });
  }
}
</script>

<style scoped>
  .row
  {
    margin-left : 3px; margin-top : 3px; margin-right : 3px;
  }
  .open-btn
  {
    color : "orange lighten-2";
  }
  .card-img
  {
   height : "200px";
  }
  .pharmacy-card
  {
    max-width : "344";
  }
</style>