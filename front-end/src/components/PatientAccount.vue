<template>
    <v-container fluid>
        <v-layout column>
            <v-card>
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
                
                <v-card-text>
                    {{user.email}} <br>
                    Broj bodova i uloga 
                    <v-text-field
                        v-model="user.name"
                        label="FirstName"></v-text-field>
                    <v-text-field
                        v-model="user.surname"
                        label="Last Name"></v-text-field>
                    <v-text-field
                        v-model="user.phoneNumber"
                        label="Phone Number"></v-text-field>

                    <v-row
                    class="mb-6"
                    no-gutters
                    >
                    <v-col
                    >
                         <v-text-field
                        v-model="user.address.streetNumber"
                        label="Street Number"></v-text-field>
                    </v-col>
                    <v-col
                    >
                        <v-text-field
                        v-model="user.address.street"
                        label="Street"></v-text-field>
                    </v-col>
                    <v-col
                    >
                         <v-text-field
                        v-model="user.address.place"
                        label="Place"></v-text-field>
                    </v-col>
                    <v-col
                    >
                        <v-text-field
                        v-model="user.address.country"
                        label="Country"></v-text-field>
                    </v-col>
                    </v-row>

                    <v-text-field
                    v-model="newPassword"
                    label="New Password"></v-text-field>

                    <v-text-field
                    v-model="confirmPassword"
                    label="Confirm Password"></v-text-field>

                </v-card-text>
                <v-card-actions>
                    <v-btn text color="primary" :loading="loading" @click.native="update">
                        Save Changes
                    </v-btn>
                </v-card-actions>
                <v-card-text>



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
                            <v-toolbar-title>Allergies</v-toolbar-title>
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
                            Nema dodatih alergija
                        </v-btn>
                        </template>
                    </v-data-table>


                </v-card-text>
            </v-card>
        </v-layout>
    </v-container>
</template>

<script>
    import TokenDecoder from '../services/token-decoder'
    import axios from "axios"
import authHeader from '../services/auth-header'
   
    export default {
        data () {
            return {
                loading: false,
                user : null,
                newPassword:'',
                confirmPassword:'',
                
                
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
                    { text: 'Delete', value: 'actions', sortable: false },
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
            }

        },
        watch: {
      dialogDelete (val) {
        val || this.closeDelete()
      },
    },

     created () {
            this.usersEmail = TokenDecoder.getUserEmail()
            this.userRole = TokenDecoder.getUserRole()
            let path = "http://localhost:9090/patients/my/allergies/" + this.usersEmail;
            axios.get(path, { headers: authHeader() }).then((response) => {
                this.orders = response.data;
            })
            
            path = "http://localhost:9090/patients/patient/" + this.usersEmail;
            axios.get(path, { headers: authHeader() }).then((response) => {
                this.user = response.data;
                console.log(this.user)
            })
        },
        methods: {
            update(){
            if(this.newPassword != this.confirmPassword){
                alert('Nepoklapanje sifre')
                return
            }
            if(this.newPassword != ""){
                this.user.password = this.newPassword
            }

            axios
            .put('http://localhost:9090/patients/edit/' + this.usersEmail, this.user, { headers: authHeader() })
            .then(response => {
              alert('Uspesno izmenjeni podaci!' + response)
            })
            .catch(response => {
                console.log(response)
              alert('Error')
            })
            },

            deleteItem (item) {
                this.selectedItem = item

                axios
                .post('http://localhost:9090/patients/delete/allergy/' + this.usersEmail + '/' + this.selectedItem.code, { headers: authHeader() })
                .then(response => {
                //alert('Uspesno izmenjeni podaci!' + response)
                console.log(response)
                })
            
                let path = "http://localhost:9090/patients/my/allergies/" + this.usersEmail;
                axios.get(path, { headers: authHeader() }).then((response) => {
                    this.orders = response.data;
                })

                this.dialogDelete = true
            },
        }
    }
</script>