<template>
<div fluid style="margin-left: 10px; margin-right: 10px; margin-top: 30px;">
  
   <v-row >
     
    <v-col cols="6" md="4">
      <div>
        <template>
          <v-row >
            <v-date-picker v-model="date" :landscape="$vuetify.breakpoint.smAndUp" ></v-date-picker>
          </v-row>
        </template>
        <template>
          <v-row>
              <v-time-picker
                v-model="time"
                format="24hr"
                :landscape="$vuetify.breakpoint.smAndUp"
              ></v-time-picker>

          </v-row>
        </template>
      </div>
      <v-btn
        v-on:click="search"
        label="Picker without buttons"
        large
        depressed 
        :value="false"
        style="margin-top: 10px;"
        @clicked="onSortClick(sorting)"
      >
        Search
      </v-btn>
    </v-col>

    <v-col cols="12" sm="6" md="8" style = "background-color: #00b2b2;">
      <PharmacySort v-if="show ==='pharmacy'" @clicked="onSortClick"></PharmacySort>
      <PharmacistSort v-if="show ==='pharmacist'"></PharmacistSort>
      <v-row class= "row" v-if="show ==='pharmacy'">
        <v-col v-for="pharmacy in pharmacies" :key="pharmacy.regNo" >
          
            <v-card
              color="white"
              dark
              style="margin-top: 5px; margin-left: 5px;"
            >
              <v-card-title class="text-h5" style="color: black;">
                {{pharmacy.name}}
              </v-card-title>

              <v-card-subtitle style="color: black;">{{pharmacy.address.street}} {{pharmacy.address.streetNumber}}, {{pharmacy.address.place}}, {{pharmacy.address.country}} 
                <br>
                {{rating(pharmacy)}}  {{pharmacy.appointmentPrice}}
              </v-card-subtitle>

              <v-card-actions>
                <v-btn style="color: black;" text v-on:click="open(pharmacy.regNo)">
                  PHARMACIST
                </v-btn>
              </v-card-actions>
            </v-card>
            <br>
        </v-col>
      </v-row>

      <v-row class= "row" v-if="show ==='pharmacist'">
        <v-col v-for="pharmacist in pharmacists" :key="pharmacist" >
          
        <v-card
          class="mx-auto"
          outlined
        >
          <v-list-item three-line>
            <v-list-item-content>
              <div class="overline mb-4">
                OVERLINE
              </div>
              <v-list-item-title class="headline mb-1">
                {{pharmacist}}
              </v-list-item-title>
              <v-list-item-subtitle>Greyhound divisely hello coldly fonwderfully</v-list-item-subtitle>
            </v-list-item-content>

            <v-list-item-avatar
              size="110"
            >
              <v-img
                style = "border: 1px solid #555;"
                class="elevation-6"
                alt=""
                src="https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light"
              ></v-img>
            </v-list-item-avatar>
          </v-list-item>

          <v-card-actions>
            <v-btn
              outlined
              style = "align: right;"
              text
              @click="reserveAppointment(pharmacist)"
            >
              ADD
            </v-btn>
          </v-card-actions>
        </v-card>
        </v-col>
      </v-row>
    </v-col>
  </v-row>
</div>
</template>


<script>
  import TokenDecoder from '../../services/token-decoder'
  import axios from 'axios'
  import PharmacySort from '@/components/patient/PharmacySort.vue'
  import PharmacistSort from '@/components/patient/PharmacistSort.vue'
import authHeader from '../../services/auth-header'
  
  export default {
    data () {
      return {
        time: '12:00',
        date: new Date().toISOString().substr(0, 10),
        pharmacies : [],
        pharmacists : [],
        show : "pharmacy",
        sortParam : "",
        regNo: null,
        pharmacist: null,
        user: null,
      }
    },
    components: {
      PharmacySort,
      PharmacistSort,
      },
    methods: {
    rating(pharmacy) {
        let avgRating = 0;
        for (const rating of pharmacy.ratings) {
          avgRating += rating;
        }
        if (pharmacy.ratings.length == 0)
          return avgRating;
        else
          return avgRating / pharmacy.ratings.length
    },
    search: function () {
        
        let path = "http://localhost:9090/api/pharmacy/apoteke/" + this.date + "_" + this.time
        this.show = "a"
        this.show = "pharmacy"
        axios
        .get(path)
        .then(response => {
          this.pharmacies = response.data
          this.onSortClick(this.sortParam)
          })
        .catch(error => {
          alert('Error: status ' + error.response.status)
          this.$router.push('/')
        })
    },
    open: function (regNo) {
      this.regNo = regNo
      let path = "http://localhost:9090/api/pharmacy/pharmacist/" + this.date + "_" + this.time + "_" + this.regNo
      this.show = "pharmacist"
      axios
        .get(path,  { headers: authHeader()})
        .then(response => {
          this.pharmacists = response.data
          })
        .catch(error => {
          alert('Error: status ' + error.response.status)
          this.$router.push('/')
        })
    },
    reserveAppointment: function(pharmacist){
      let usersEmail = TokenDecoder.getUserEmail()
      this.pharmacist = pharmacist
      axios
      .post('http://localhost:9090/api/pharmacy/add/pharmacist/appointment/' + 
                 this.date + "_" + this.time + "_" + this.pharmacist + "_" + usersEmail + "_" + this.regNo , null, { headers: authHeader()} )
      .then(response => {
      console.log(response)
      })
      window.localtion.reload()
    },
    onSortClick: function (sorting) {
      this.sortParam = sorting
      if (sorting == "RatingAsc") {
        this.pharmacies.sort(function (a, b) {
          let ratingA = 0;
          let ratingB = 0;
          for (const rating of a.ratings)
            ratingA += rating;
          if (a.ratings.length != 0)
            ratingA = ratingA / a.ratings.length

          for (const rating of b.ratings)
            ratingB += rating;
          if (b.ratings.length != 0)
            ratingB = ratingB / b.ratings.length

          if (ratingA <ratingB) {
            return -1;
          }
          if (ratingA > ratingB) {
            return 1;
          }
          return 0;
        });
      } else if (sorting == "PriceAsc")
      {
        this.pharmacies.sort(function (a, b) {
          if (a.appointmentPrice < b.appointmentPrice) {
            return -1;
          }
          if (a.appointmentPrice > b.appointmentPrice) {
            return 1;
          }
          return 0;
        });
      } else if (sorting == "PriceDesc")
      {
        
        this.pharmacies.sort(function (a, b) {
          if (a.appointmentPrice < b.appointmentPrice) {
            return 1;
          }
          if (a.appointmentPrice > b.appointmentPrice) {
            return -1;
          }
          return 0;
        });

      }
    },
  },
}
</script>
