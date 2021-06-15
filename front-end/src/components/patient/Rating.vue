<template>
  <div>
  <v-row
    align="center"
    justify="space-around"
    style = "padding-top: 25px; padding-bottom: 25px;"
  >
    <v-btn text @click="pharmacy">
      Pharmacy
    </v-btn>
    <v-btn text @click="pharmacist">
      Pharmacist
    </v-btn>
    <v-btn text @click="dermatologist">
      Dermatologist
    </v-btn>
    <v-btn text @click="medicine">
      Medicine
    </v-btn>
  </v-row>

  <div v-if="contoller == 'medicine'">
      <v-data-table
    :headers="headers"
    :items="orders"
    sort-by="calories"
    class="elevation-1"
  >
    <template v-slot:top>
      <v-toolbar
        flat
      >
        <v-toolbar-title>Medicines</v-toolbar-title>
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
    <template v-slot:item.actions="{ item }">
      <v-icon
        small
        @click="medicineList(item)"
      >
        mdi-plus
      </v-icon>
    </template>
    <template v-slot:no-data>
      <v-btn
        text
        color="primary"
      >
        Nemate preuzetih lijekova koje biste mogli ocjeniti
      </v-btn>
    </template>
  </v-data-table>
  </div>

  <RatingMedicine v-if="contoller == 'RatingMedicine'"/> 
  <DermatologistRating v-if="contoller == 'DermatologistRating'"/>
  <PharmacistRating v-if="contoller == 'PharmacistRating'"/>
  

  <div v-if="contoller == 'dermatologist'">
    <v-data-table
    :headers="headers1"
    :items="appointments"
    class="elevation-1"
  >
    <template v-slot:item.actions="{ item }">
      <v-icon
        small
        @click="dermatologistList(item)"
      >
        mdi-plus
      </v-icon>
    </template>
  </v-data-table>
  </div>

  <div v-if="contoller == 'pharmacist'">
    <v-data-table
    :headers="headers2"
    :items="pharmacistAppointments"
    class="elevation-1"
  >
    <template v-slot:item.actions="{ item }">
      <v-icon
        small
        @click="pharmacistList(item)"
      >
        mdi-plus
      </v-icon>
    </template>
  </v-data-table>
  </div>


  </div>
</template>


<script>
  import TokenDecoder from '../../services/token-decoder'
  import axios from "axios"
  import RatingMedicine from '@/components/patient/RatingMedicine.vue'
  import DermatologistRating from '@/components/patient/DermatologistRating.vue'
  import PharmacistRating from '@/components/patient/PharmacistRating.vue'
  import authHeader from '../../services/auth-header'
  export default {
    data: () => ({
      dialog: false,
      headers: [
        {
          text: 'Medicine',
          align: 'start',
          value: 'name',
        },
        { text: 'Type', value: 'type' },
        { text: 'Manufacturer', value: 'manufacturer' },
        { text: 'Actions', value: 'actions', sortable: false },
      ],
      headers1: [
        {
          text: 'Dermatologist name',
          align: 'start',
          value: 'name',
        },
        { text: 'Dermatologist surname', value: 'surname' },
        { text: 'Dermatologist email', value: 'email' },
        { text: 'Actions', value: 'actions', sortable: false },
      ],
      headers2: [
        {
          text: 'Pharmacist name',
          align: 'start',
          value: 'name',
        },
        { text: 'Pharmacist surname', value: 'surname' },
        { text: 'Pharmacist email', value: 'email' },
        { text: 'Pharmacy', value: 'pharmacyName' },
        { text: 'Phone Number', value: 'phoneNumber' },
        { text: 'Actions', value: 'actions', sortable: false },
      ],
      appointments: [],
      orders: [],
      pharmacistAppointments: [],
      selectedItem: null,
      medicineQuantity: 0,
      userRole: null,
      contoller: "medicine",

    }),
    components: {
        RatingMedicine,
        DermatologistRating,
        PharmacistRating
    },
     created () {
       this.userRole = TokenDecoder.getUserRole()
       console.log(this.userRole)
            let path = "http://localhost:9090/patients/medicine/rating/" + TokenDecoder.getUserEmail();
            axios.get(path,  { headers: authHeader() }).then((response) => {
                this.orders = response.data;
            })
       let usersEmail = TokenDecoder.getUserEmail()
            let path1 = "http://localhost:9090/api/dermatologists/dermatologist/rating/" + usersEmail ;
            axios.get(path1,  { headers: authHeader() }).then((response) => {
                this.appointments = response.data;
            })
            let path2 = "http://localhost:9090/api/pharmacist/pharmacy/rating/" + usersEmail ;
            axios.get(path2,  { headers: authHeader() }).then((response) => {
                this.pharmacistAppointments = response.data;
            })
    },

    methods: {
      pharmacy(){this.contoller = "pharmacy"},
      pharmacist(){this.contoller = "pharmacist"},
      dermatologist(){this.contoller = "dermatologist"},
      medicine(){this.contoller = "medicine"},

      medicineList (item) {
        this.selectedItem = item
        //localStorage.setItem('pacijent',localStorage.getItem('medicineRating'));
        localStorage.setItem('medicineRating', item.code);
        this.contoller = "RatingMedicine"
      },

      dermatologistList (item) {
        this.selectedItem = item
        console.log(item)
        //localStorage.setItem('pacijent',localStorage.getItem('medicineRating'));
        localStorage.setItem('dermatologistRating', item.email);
        this.contoller = "DermatologistRating"
      },
      
      pharmacistList (item) {
        this.selectedItem = item
        console.log(item)
        //localStorage.setItem('pacijent',localStorage.getItem('medicineRating'));
        localStorage.setItem('pharmacistRating', item.email);
        this.contoller = "PharmacistRating"
      },

    },
  }
</script>