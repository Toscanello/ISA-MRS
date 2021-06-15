<template>
  <v-card
    class="elevation-16 mx-auto"
    width="100%"
    color="white"
  >
    <v-card-title class="text-h5" style="align: center;">
      {{dermatologist.name}} {{dermatologist.surname}}
    </v-card-title>
    <v-card-text>
        Tip: 

      <div class="text-center mt-12">
        <v-rating
          v-model="rating"
          color="yellow darken-3"
          background-color="grey darken-1"
          empty-icon="$ratingFull"
          half-increments
          hover
          large
        ></v-rating>
      </div>
    </v-card-text>
    <v-divider></v-divider>
    <v-card-actions class="justify-space-between">
      <v-btn @click="rate" text>
        Rate
      </v-btn>
    </v-card-actions>
  </v-card>
</template>


<script>
  import axios from "axios";
  import TokenDecoder from '../../services/token-decoder'
  import authHeader from '../../services/auth-header'
  export default {
    data: () => ({
      rating: 0.0,
      dermatologistEmail: null,
      dermatologist: null,
    }),
    created (){
      this.dermatologistEmail = localStorage.getItem('dermatologistRating')

      let path = "http://localhost:9090/api/dermatologists/derm/" + this.dermatologistEmail;
      axios.get(path, { headers: authHeader() }).then((response) => {
          console.log( response.data)
          this.dermatologist = response.data;
      })
      let path1 = 'http://localhost:9090/patients/get/dermatologist/rating/' + TokenDecoder.getUserEmail() + '/' + this.dermatologistEmail;
      axios.get(path1, { headers: authHeader() }).then((response) => {
          this.rating = response.data;
      })
     
    },
    methods: {
      rate(){
        axios
        .post('http://localhost:9090/patients/dermatologist/rate/' + TokenDecoder.getUserEmail() + '/' + this.dermatologistEmail + '/' + this.rating, null, { headers: authHeader() } )
        .then(response => {
          console.log(response)
        })
          
      }
    }
  }
</script>