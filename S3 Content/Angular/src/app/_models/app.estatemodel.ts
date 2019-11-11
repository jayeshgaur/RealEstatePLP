import { UserModel } from "./app.usermodel";
import { ImageModel } from "./app.imagemodel";
import { AddressModel } from "./app.addressmodel";

export class EstateModel{
    estateId:any;
    estateName:any;
    estateAddress:AddressModel;
    estateArea:any;
    estatePrice:any;
    estateOwner:UserModel;
    imageList:ImageModel[];
    thumbnail:string;

}