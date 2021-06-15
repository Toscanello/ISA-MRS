<template>
  <v-data-table
    :headers="headers"
    :items="appointments"
    sort-by="calories"
    class="elevation-1"
  >
    <template v-slot:item.time="{ item }">

        {{ item.time.split("T")[0]}} {{ item.time.split("T")[1]}}
    </template>
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
                      label="Dessert name"
                    >{{editedItem.id}}</v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      label="Calories"
                    >{{editedItem.id}}</v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      label="Fat (g)"
                    >{{editedItem.id}}</v-text-field>
                  </v-col>
                  
                </v-row>
              </v-container>
            </v-card-text>
          </v-card>
        </v-dialog>
        <v-dialog v-model="dialogDelete" max-width="500px">
          <v-card>
            <v-card-title class="headline">Zakazan sastanak</v-card-title>
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
        THERE IS NO FREE APPOINTMENTS
      </v-btn>
    </template>
  </v-data-table>
</template>

<script>
  import TokenDecoder from '../../services/token-decoder'
  import axios from "axios";
import authHeader from '../../services/auth-header';
  export default {
    name: "DermatologistAppointment",
    data: () => ({
      dialog: false,
      dialogDelete: false,
      headers: [
        {
          text: 'Dermatologist name',
          align: 'start',
          value: 'dermatologist.name',
        },
        { text: 'Dermatologist surname', value: 'dermatologist.surname' },
        { text: 'Dermatologist email', value: 'dermatologist.email' },
        { text: 'Start', value: 'time' },
        { text: 'Duration', value: 'duration' },
        { text: 'Price', value: 'price' },
        { text: 'Actions', value: 'actions', sortable: false },
      ],
      appointments: [],
      selectedItem: null,
      editedItem: {
       id:null,time:"",duration:"",dermatologist:{email:"",name:"",surname:""},price:1000.0},
      defaultItem: {
       id:null,time:"",duration:"",dermatologist:{email:"",name:"",surname:""},price:1000.0},
    }),


    watch: {
      dialogDelete (val) {
        val || this.closeDelete()
      },
    },

     created () {
            let path = "http://localhost:9090/api/pharmacy/pharmacy/appointments/" + this.$route.params.regNo ;
            axios.get(path, { headers: authHeader() }).then((response) => {
                this.appointments = response.data;
            })
    },

    methods: {

      deleteItem (item) {
        this.selectedItem = item
        this.dialogDelete = true
      },

      deleteItemConfirm () {
        
        let usersEmail = TokenDecoder.getUserEmail()
        
        axios
        .post('http://localhost:9090/api/pharmacy/new/appointment/' + usersEmail, this.selectedItem, { headers: authHeader() } )
        .then(response => {
          console.log(response)
        })

        this.closeDelete()
      },

      closeDelete () {
        this.dialogDelete = false
        window.location.reload()
      }, 

    },
  }
</script>