<template>
  <v-card
    class="elevation-16 mx-auto"
    width="100%"
    color="white"
  >
    <v-card-title class="text-h5" style="align: center;">
      Naziv lijeka {{medicine.name}}
    </v-card-title>
    <v-card-text>
        Tip: {{medicine.type}}
        <br>
        Proizvodjac: {{medicine.manufacturer}}
        <br>
        Forma lijeka: {{medicine.drugForm}}
        <br>
        Sastav: {{medicine.composition}}
        <br>
        Opis lijeka: {{medicine.description}}

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
import authHeader from '../../services/auth-header';
  export default {
    data: () => ({
      rating: 0.0,
      medicineCode: null,
      medicine: null,
    }),
    created (){
      this.medicineCode = localStorage.getItem('medicineRating')

      let path = "http://localhost:9090/api/medicine/one/" + this.medicineCode;

      let path1 = 'http://localhost:9090/patients/get/medicine/rating/' + TokenDecoder.getUserEmail() + '/' + this.medicineCode;
      axios.get(path, { headers: authHeader() }).then((response) => {
          this.medicine = response.data;
      })
      
      axios.get(path1, { headers: authHeader() }).then((response) => {
          this.rating = response.data;
      })
    },
    methods: {
      rate(){
        axios
        .post('http://localhost:9090/patients/medicine/rate/' + TokenDecoder.getUserEmail() + '/' + this.medicineCode + '/' + this.rating, null, { headers: authHeader() } )
        .then(response => {
          console.log(response)
        })
          
      }
    }
  }
</script>