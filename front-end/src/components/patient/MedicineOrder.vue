<template>
  <v-data-table
    :headers="headers"
    :items="orders"
    sort-by="calories"
    class="elevation-1"
  >
    <template v-slot:item.endTime="{ item }">
      {{ item.endTime.split("T")[0]}} {{ item.endTime.split("T")[1]}}
    </template>
    <template v-slot:top>
      <v-toolbar
        flat
      >
        <v-toolbar-title>My Orders</v-toolbar-title>
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
          
        </v-dialog>
        <v-dialog v-model="dialogDelete" max-width="500px">
          <v-card>
            <v-card-title class="headline">Porudzbinu je moguce otkazati najkasnije 24h prije preuzimanja.</v-card-title>
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
        mdi-delete
      </v-icon>
    </template>
    <template v-slot:no-data>
      <v-btn
        text
        color="primary"
      >
        No orders.
      </v-btn>
    </template>
  </v-data-table>
</template>

<script>
import TokenDecoder from '../../services/token-decoder'
    import axios from "axios";
  export default {
    data: () => ({
      dialog: false,
      dialogDelete: false,
      headers: [
        {
          text: 'Medicine',
          align: 'start',
          value: 'medicine.medicineDTO.name',
        },
        { text: 'Pharmacy', value: 'medicine.pharmacyDTO.name' },
        { text: 'Quantity', value: 'quantity' },
        { text: 'Price', value: 'price' },
        { text: 'Date', value: 'endTime' },
        { text: 'Actions', value: 'actions', sortable: false },
      ],
      desserts: [],
      orders: [],
      editedIndex: -1,
      editedItem: {
        id:0,
        medicine:{medicineDTO
        :{code:"",name:"",type:"",manufacturer:"",drugForm:"",composition:"",description:"",category:""},
        pharmacyDTO:{regNo:"",name:"",
        address:{id:1,street:" ",streetNumber:"",place:"",country:"",
        location:{geoWidth:0.0,geoHeight:0.0}},ratings:[0],appointmentPrice:0.0},price:0.0,pricingStart:"",pricingEnd:""}
        ,
        quantity:0.0,
        price:0.0,
        startTime: null,
        endTime: null,      
      },
      defaultItem: {
        id:0,
        medicine:{medicineDTO
        :{code:"",name:"",type:"",manufacturer:"",drugForm:"",composition:"",description:"",category:""},
        pharmacyDTO:{regNo:"",name:"",
        address:{id:1,street:" ",streetNumber:"",place:"",country:"",
        location:{geoWidth:0.0,geoHeight:0.0}},ratings:[0],appointmentPrice:0.0},price:0.0,pricingStart:"",pricingEnd:""}
        ,
        quantity:0.0,
        price:0.0,
        startTime: null,
        endTime: null,       
        selectedItem : null,
        localtime: null,
        isOk: "ok",
      },
    }),


    watch: {
      dialogDelete (val) {
        val || this.closeDelete()
      },
    },

     created () {
      let usersEmail = TokenDecoder.getUserEmail()
            let path = "http://localhost:9090/patients/orders/patient/" + usersEmail;
            axios.get(path).then((response) => {
                this.orders = response.data;
                console.log(this.orders)
            })
    },

    methods: {

      deleteItem (item) {
        this.selectedItem = item
 
        let path = "http://localhost:9090/patients/get/order/" + item.endTime;
            axios.get(path).then((response) => {
                this.isOk = response.data;
                if(this.isOk == "ok"){
              axios
                  .post('http://localhost:9090/patients/delete/order' , this.selectedItem, {
                    
                  })
                  .then(response => {
                    console.log(response)
                  })
                  .catch(response => {
                      console.log(response)
                    alert('Error')
                  })

                let usersEmail = TokenDecoder.getUserEmail()
                  let path1 = "http://localhost:9090/patients/orders/patient/" + usersEmail;
                  axios.get(path1).then((response) => {
                      this.orders = response.data;
                      this.$router.go()
                  })
              }else{
                this.dialogDelete = true
              }
            })
        
      },

      deleteItemConfirm () {
         
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