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
                        label="FirstName"
                        ></v-text-field>
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
            </v-card>
        </v-layout>
    </v-container>
</template>

<script>
    import TokenDecoder from '../services/token-decoder'
    import axios from "axios";
import authHeader from '../services/auth-header';
    export default {
        name: "MedicalAccount",
        data () {
            return {
                loading: false,
                user : null,
                newPassword:'',    
                confirmPassword:'',
            }
        },
        created() {
            let usersEmail = TokenDecoder.getUserEmail()
            let role = TokenDecoder.getUserRole();
            let path="";
            if(role==="ROLE_PHARMACIST")
                path = "http://localhost:9090/api/pharmacist/pharm/" + usersEmail;
            else
                path = "http://localhost:9090/api/dermatologists/derm/" + usersEmail;
            axios.get(path, {headers: authHeader()}).then((response) => {
                this.user = response.data;
                console.log(this.user)
            })
        },
        methods: {
            update(){
                let usersEmail = TokenDecoder.getUserEmail();
                if(this.newPassword != this.confirmPassword){
                alert('Nepoklapanje sifre')
                return
            }
                if(this.newPassword != "")
                    this.user.password = this.newPassword
                let role = TokenDecoder.getUserRole();
                let path="";
                if(role==="ROLE_PHARMACIST")
                    path = "http://localhost:9090/api/pharmacist/edit/"+ usersEmail;
                else
                    path = "http://localhost:9090/api/dermatologists/edit/"+usersEmail;
                console.log(this.user);
                axios
                .put(path, this.user, {headers: authHeader()})
                .then(
                    alert('Successfully edited!')
                )
            },
        }
    }
</script>