<template>
  <v-data-table
    :headers="headers"
    :items="orders"
    sort-by="calories"
    class="elevation-1"
  >
    <template v-slot:item.address="{ item }">

        {{item.address.street}} {{item.address.streetNumber}}, {{item.address.place}} 
    </template>
    <template v-slot:item.ratings="{ item }">

        {{rating(item.ratings)}}
    </template>
    <template v-slot:top>
      <v-toolbar
        flat
      >
        <v-toolbar-title>Pharmacies</v-toolbar-title>
        <v-divider
          class="mx-4"
          inset
          vertical
        ></v-divider>
        <v-spacer></v-spacer>
        <v-dialog
          v-model="dialog"
          max-width="500px"
        >
          <v-card>

            <v-card-text>
              <v-container>
                <v-row>
                </v-row>
              </v-container>
            </v-card-text>
          </v-card>
        </v-dialog>
        
      </v-toolbar>
    </template>
    <template v-slot:no-data>
      <v-btn
        text
        color="primary"
      >
        THERE IS NO RESERVATIONS(DEDAJ REDIREKCIJU NA STRANICU ZA REZERVISANJE LIJEKOVA)
      </v-btn>
    </template>
  </v-data-table>
</template>

<script>
  import TokenDecoder from '../../services/token-decoder'
  import axios from "axios";
import authHeader from '../../services/auth-header';
  export default {
    data: () => ({
      picker: new Date().toISOString().substr(0, 10),
      dialog: false,
      dialogDelete: false,
      headers: [
        {
          text: 'Pharmacy',
          align: 'start',
          value: 'name',
        },
        { text: 'Address', value: 'address' },
        { text: 'Rating', value: 'ratings' },
      ],
      orders: [],
      editedItem: {
       regNo:"",name:"",
       address:{id:1,street:"",streetNumber:"",place:"",country:"",location:{geoWidth:0,geoHeight:0}},
       ratings:[],appointmentPrice:0.0         
      },
      defaultItem: {
        regNo:"",name:"",
        address:{id:1,street:"",streetNumber:"",place:"",country:"",location:{geoWidth:0,geoHeight:0}},
        ratings:[],appointmentPrice:0.0
      },
    }),

     created () {
        let email = TokenDecoder.getUserEmail();
        let path = "http://localhost:9090/patients/advertising/" + email;
        axios.get(path, { headers: authHeader() }).then((response) => {
            this.orders = response.data;
        })
    },

    methods: {
       rating(item) {
        let avgRating = 0;
        for (const rating of item) {
          avgRating += rating;
        }
        if (item.length == 0)
          return avgRating;
        else
          return avgRating / item.length
      }

    },
  }
</script>