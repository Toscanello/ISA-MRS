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
            <v-btn
                large
                depressed
                :value="false"
                v-on:click="searchFun"
              >
                <v-icon>mdi-text-search</v-icon>
              </v-btn>
          </v-toolbar>
          <v-row class= "row">
            <v-col v-for="pharmacy in pharmacies" :key="pharmacy.regNo" cols="12" sm="6" md="4" lg="3">
              <v-card
                dark
                style = "background-color:#424242;"
              >
                <div class="d-flex flex-no-wrap justify-space-between">
                  <div>
                    <v-card-title
                      class="text-h5"
                    >{{pharmacy.name}}</v-card-title>

                    <v-card-subtitle>{{pharmacy.address.street}} {{pharmacy.address.streetNumber}}, {{pharmacy.address.place}}, {{pharmacy.address.country}}
                    </v-card-subtitle>

                    <v-card-actions v-if="userRole == 'ROLE_USER'">
                      <v-btn 
                      @click="open(pharmacy)"
                      text
                      >
                        Reserve
                      </v-btn>
                      <v-spacer></v-spacer>
                      <span class="grey--text text--lighten-2 text-caption mr-2">
                        
                         <v-rating
                        v-model="rating"
                        background-color="white"
                        color="yellow accent-4"
                        dense
                        half-increments
                        hover
                        size="18"
                      ></v-rating>  
                      </span>
                      ({{ rating }})
                     
                    </v-card-actions>
                  </div>
                </div>
              </v-card>
      </v-col>
    </v-row>
  </div>
</template>


<script>
import TokenDecoder from '../../services/token-decoder'
import axios from 'axios'
export default {
    name: 'PharmacyList',
    data: () => ({
    drawer: false,
    group: null,
    pharmacies: [],
    userRole: null,
    search: "",
    rating: 4.5,
  }),
  created() {
    this.userRole = TokenDecoder.getUserRole()
    axios.get("http://localhost:9090/api/pharmacy/all").then((resp) => {
      this.pharmacies = resp.data;
    });
  },
  methods: {
    open(pharmacy){
      this.$router.push('/dermatologistAppointment/' + pharmacy.regNo)
    },
    searchFun(){ 
      if(this.search == ""){
        axios.get("http://localhost:9090/api/pharmacy/all").then((resp) => {
          this.pharmacies = resp.data;
        });
      }else{
        let path = "http://localhost:9090/api/pharmacy/search/" + this.search; 
        axios.get(path).then((resp) => {
        this.pharmacies = resp.data;
        });
      }
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