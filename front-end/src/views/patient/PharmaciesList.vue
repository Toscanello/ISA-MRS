<template>
  <div
  >     
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
            style="padding-right: 20px;"
            
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
            :loading="loading"
            
            
            style="margin-top: 5px;"
          >
            <template slot="progress">
              <v-progress-linear
                color="deep-purple"
                height="10"
                indeterminate
              ></v-progress-linear>
            </template>

            <GmapMap
                :center="{
                  lat: pharmacy.address.location.geoHeight,
                  lng: pharmacy.address.location.geoWidth
                  }"
                :zoom="9"
                style="width:100%; height: 250px">
                <GmapMarker
                  :position="{
                  lat: pharmacy.address.location.geoHeight,
                  lng: pharmacy.address.location.geoWidth
                  }"></GmapMarker>
              </GmapMap>
 

            <v-card-title>{{pharmacy.name}}</v-card-title>

            <v-card-text>
              <v-row
                align="center"
                class="mx-0"
              >
                <v-rating
                  :value="4.5"
                  color="amber"
                  dense
                  half-increments
                  readonly
                  size="14"
                ></v-rating>

                <div class="grey--text ml-4">
                  4.5 (413)
                </div>
              </v-row>

              <div class="my-4 subtitle-1">
                {{pharmacy.address.street}} {{pharmacy.address.streetNumber}}, {{pharmacy.address.place}}, {{pharmacy.address.country}}
              </div>

              <div>Ovdje moze neki dodatni opis apoteke.</div>
            </v-card-text>

            <v-card-actions>
              <v-btn
                color="deep-purple lighten-2"
                text
               @click="open(pharmacy)"
              >
                Reserve
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
  },
  methods: {
    open(pharmacy){
      this.$router.push('/dermatologistAppointment/' + pharmacy.regNo)
    }
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