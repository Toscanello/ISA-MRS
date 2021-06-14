<template>
  <v-card
    class="elevation-16 mx-auto"
    width="100%"
    color="white"
  >
    <v-card-title class="text-h5" style="align: center;">
      {{pharmacist.name}} {{pharmacist.surname}}
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
  export default {
    data: () => ({
      rating: 0.0,
      pharmacistEmail: null,
      pharmacist: null,
    }),
    created (){
      this.pharmacistEmail = localStorage.getItem('pharmacistRating')

      let path = "http://localhost:9090/api/pharmacist/pharm/" +  this.pharmacistEmail;
      axios.get(path).then((response) => {
          console.log( response.data)
          this.pharmacist = response.data;
      })

      let path1 = 'http://localhost:9090/patients/get/pharmacist/rating/' + TokenDecoder.getUserEmail() + '/' + this.pharmacistEmail;
      axios.get(path1).then((response) => {
          this.rating = response.data;
      })
    },
    methods: {
      rate(){
        axios
        .post('http://localhost:9090/patients/pharmacist/rate/' + TokenDecoder.getUserEmail() + '/' + this.pharmacistEmail + '/' + this.rating, null )
        .then(response => {
          console.log(response)
        })
          
      }
    }
  }
</script>