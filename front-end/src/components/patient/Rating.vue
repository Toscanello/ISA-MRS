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
  </div>
</template>


<script>
  import TokenDecoder from '../../services/token-decoder'
  import axios from "axios"
  import RatingMedicine from '@/components/patient/RatingMedicine.vue'
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
      orders: [],
      selectedItem: null,
      medicineQuantity: 0,
      userRole: null,
      contoller: "medicine",

    }),
    components: {
        RatingMedicine
    },
     created () {
       this.userRole = TokenDecoder.getUserRole()
       console.log(this.userRole)
            let path = "http://localhost:9090/patients/medicine/rating/" + TokenDecoder.getUserEmail();
            axios.get(path).then((response) => {
                this.orders = response.data;
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

    },
  }
</script>