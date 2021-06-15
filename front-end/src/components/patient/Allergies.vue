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
                </v-row>
              </v-container>
            </v-card-text>
          </v-card>
        </v-dialog>
        <v-dialog v-model="dialogDelete" max-width="500px" v-if="userRole === 'ROLE_USER'">
          <v-card>
            <v-card-title class="headline">Cofirm order</v-card-title>
            
            
            <label for="quantity"> <b>Uspesno ste dodali alergiju na lek</b> </label>
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
          text: 'Medicine',
          align: 'start',
          value: 'name',
        },
        { text: 'Type', value: 'type' },
        { text: 'Drug Form', value: 'drugForm' },
        { text: 'Actions', value: 'actions', sortable: false },
      ],
      orders: [],
      selectedItem: null,
      medicineQuantity: 0,
      editedItem: {
      code:"",name:"",type:"",manufacturer:"",drugForm:"",composition:"",description:"",category:""   
      },
      defaultItem: {
        code:"",name:"",type:"",manufacturer:"",drugForm:"",composition:"",description:"",category:""           
      },
      userRole: null,
      usersEmail: null,
    }),


    watch: {
      dialogDelete (val) {
        val || this.closeDelete()
      },
    },

     created () {
       this.userRole = TokenDecoder.getUserRole()
       console.log(this.userRole)
            let path = "http://localhost:9090/api/medicine/findall";
            axios.get(path, { headers: authHeader() }).then((response) => {
                this.orders = response.data;
            })
    },

    methods: {

      deleteItem (item) {
        this.selectedItem = item
        this.dialogDelete = true
      },

      deleteItemConfirm () {
          ///allergy/{email}/{code}
          this.usersEmail = TokenDecoder.getUserEmail()
          axios
              .post('http://localhost:9090/patients/allergy/' + this.usersEmail + '/' + this.selectedItem.code , null, { headers: authHeader() } )
              .then(response => {
                console.log(response)
              })
        this.closeDelete()
      },

      closeDelete () {
        
        this.dialogDelete = false
      },

    },
  }
</script>