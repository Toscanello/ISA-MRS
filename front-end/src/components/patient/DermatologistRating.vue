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
  //import TokenDecoder from '../../services/token-decoder'
  export default {
    data: () => ({
      rating: 0.0,
      dermatologistEmail: null,
      dermatologist: null,
    }),
    created (){
      this.dermatologistEmail = localStorage.getItem('dermatologistRating')

      let path = "http://localhost:9090/api/dermatologists/derm/" + this.dermatologistEmail;
      console.log(path)
      axios.get(path).then((response) => {
          console.log( response.data)
          this.dermatologist = response.data;
      })
      console.log("blaaaa")
      console.log(this.dermatologistEmail)
      console.log(this.dermatologist)
     
    },
    methods: {
      rate(){
        /*axios
        .post('http://localhost:9090/patients/medicine/rate/' + TokenDecoder.getUserEmail() + '/' + this.medicineCode + '/' + this.rating, null )
        .then(response => {
          console.log(response)
        })
          */
      }
    }
  }
</script>