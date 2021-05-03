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
                    {{form.contactEmail}}
                    <v-text-field
                        v-model="form.firstName"
                        label="FirstName"></v-text-field>
                    <v-text-field
                        v-model="form.lastName"
                        label="Last Name"></v-text-field>
                    <v-text-field
                        v-model="form.phoneNumber"
                        label="Phone Number"></v-text-field>

                    <v-row
                    class="mb-6"
                    no-gutters
                    >
                    <v-col
                    >
                         <v-text-field
                        v-model="form.streetNumber"
                        label="Street Number"></v-text-field>
                    </v-col>
                    <v-col
                    >
                        <v-text-field
                        v-model="form.street"
                        label="Street"></v-text-field>
                    </v-col>
                    <v-col
                    >
                         <v-text-field
                        v-model="form.place"
                        label="Place"></v-text-field>
                    </v-col>
                    <v-col
                    >
                        <v-text-field
                        v-model="form.country"
                        label="Country"></v-text-field>
                    </v-col>
                    </v-row>


                    <v-text-field
                    v-model="form.newPassword"
                    label="New Password"></v-text-field>

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
    export default {
        data () {
            return {
                loading: false,
                form: {
                    firstName: 'John',
                    lastName: 'Doe',
                    contactEmail: 'john@doe.com',
                    phoneNumber: '064-2744/333',
                    streetNumber:'7',
                    street:'Bulevar Despota Stefana',
                    place:'Novi Sad',
                    country:'Srbija',
                    newPassword:'',
                },
            }
        },
         methods: {
            update(){
                let role = TokenDecoder.getUserEmail()
            console.log(role)
            },
         }

    }
</script>