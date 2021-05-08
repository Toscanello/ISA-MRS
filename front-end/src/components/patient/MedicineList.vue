<template>
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
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      v-model="editedItem.id"
                      label="Dessert name"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      v-model="editedItem.quantity"
                      label="Calories"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      v-model="editedItem.price"
                      label="Fat (g)"
                    ></v-text-field>
                  </v-col>
                  
                </v-row>
              </v-container>
            </v-card-text>
          </v-card>
        </v-dialog>
        <v-dialog v-model="dialogDelete" max-width="500px">
          <v-card>
            <v-card-title class="headline">Cofirm order</v-card-title>
            <label for="quantity">Quantity: </label>
            <input type="number" id="quantity" name="quantity" min="1" max="5" value="1">
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="closeDelete">Cancel</v-btn>
              <v-btn color="blue darken-1" text @click="deleteItemConfirm">OK</v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
    </template>
    <template v-slot:item.actions="{ item }">
      <v-icon
        small
        @click="deleteItem(item)"
      >
        mdi-plus
      </v-icon>
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
  import axios from "axios";
  export default {
    data: () => ({
      dialog: false,
      dialogDelete: false,
      headers: [
        {
          text: 'Medicine',
          align: 'start',
          value: 'medicineDTO.name',
        },
        { text: 'Pharmacy', value: 'pharmacyDTO.name' },
        { text: 'Price', value: 'price' },
        { text: 'Actions', value: 'actions', sortable: false },
      ],
      orders: [],
      editedIndex: -1,
      editedItem: {
       id:0,
        medicineDTO
        :{code:"",name:"",type:"",manufacturer:"",drugForm:"",composition:"",description:"",category:""},
        pharmacyDTO:{regNo:"",name:"",
        address:{id:1,street:" ",streetNumber:"",place:"",country:"",
        location:{geoWidth:0.0,geoHeight:0.0}},ratings:[0],appointmentPrice:0.0},price:0.0,pricingStart:"",pricingEnd:""     
           
      },
      defaultItem: {
        id:0,
        medicineDTO
        :{code:"",name:"",type:"",manufacturer:"",drugForm:"",composition:"",description:"",category:""},
        pharmacyDTO:{regNo:"",name:"",
        address:{id:1,street:" ",streetNumber:"",place:"",country:"",
        location:{geoWidth:0.0,geoHeight:0.0}},ratings:[0],appointmentPrice:0.0},price:0.0,pricingStart:"",pricingEnd:""           
      },
    }),


    watch: {
      dialogDelete (val) {
        val || this.closeDelete()
      },
    },

     created () {
            let path = "http://localhost:9090/api/medicine/all" ;
            axios.get(path).then((response) => {
                this.orders = response.data;
                console.log(this.orders)
            })
    },

    methods: {

      deleteItem (item) {
          console.log(item)
          
        //this.editedIndex = this.desserts.indexOf(item)
        //this.editedItem = Object.assign({}, item)
        this.dialogDelete = true
      },

      deleteItemConfirm () {
          console.log(document.getElementById("quantity").value)
        //this.desserts.splice(this.editedIndex, 1)
        this.closeDelete()
      },

      closeDelete () {
        this.dialogDelete = false
        //this.$nextTick(() => {
          //this.editedItem = Object.assign({}, this.defaultItem)
          //this.editedIndex = -1
       //})
      },

    },
  }
</script>