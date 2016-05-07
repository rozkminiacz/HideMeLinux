package com.michalik;

import java.io.UnsupportedEncodingException;

/**
 * Created by michalik on 02.05.16.
 */
public class Steg {
    private int[][] image;
    private String string;
    private String[] binarString;

    public Steg(int[][] image, String string){
        this.image = image;
        this.string = string;
    }

    void stringToBinaryConversion() throws UnsupportedEncodingException {
        byte[] infoBin= string.getBytes("ASCII");
        String[] w = new String[infoBin.length];
        int i=0;
        for (byte b : infoBin) {
            w[i] = "0"+Integer.toBinaryString(b);

            if(w[i].length()==7){
                w[i]="0"+w[i];
            }
            System.out.println(w[i]);
            i++;
        }
        binarString=w;
    }

    void codeBinaryToIntImage(){
        int stringIterator = 0;
        for(int i=0; i<binarString.length; i++){
            char[] c = binarString[i].toCharArray();
            for(int j=0; j<8; j++){
                System.out.print(c[j]);
            }
            System.out.println("\n");
        }
        for(int i=0; i<image.length; i++){
            for(int j=0; j<image[1].length-8;){
                if(stringIterator>binarString.length-1){
                    break;
                }
                char[] c = binarString[stringIterator].toCharArray();

                for(int k=0; k<8; k++){
                    System.out.print("image["+i+"]["+(j+k)+"]="+image[i][(j+k)]+" --> "+c[k]+" --> ");
                    image[i][j+k]=codedOutput(image[i][j+k], c[k]);
                    System.out.println(image[i][j+k]);

                }
                stringIterator++;
                j+=8;

            }
        }
    }

    int codedOutput(int im, char b){
        int px=im;

        if(im%2==0 && b=='0'){
            return px;
        }
        if(im%2==0 && b=='1'){
            return px+1;
        }
        if(im%2!=0 && b=='0'){
            return px-1;
        }
        if(im%2!=0 && b=='1'){
            return px;
        }

        return px;
    }

    void decodeBinaryFromIntImage(){
//        String[] strings = new String[4];
        String tmp = "";
        for(int i=0; i<1; i++){
            for(int j=0; j<image[1].length-8;){
                //weź 8 pikseli z rzędu

                for(int k=0; k<8; k++){

                    if(image[i][j+k]%2==0){
                        //System.out.print("0");
                        tmp+="0";
                    }
                    if(image[i][j+k]%2!=0){
                        //System.out.print("1");
                        tmp+="1";
                    }
                    //System.out.print(tmp);
                }
                tmp+=" ";


                //System.out.println(tmp);
                j+=8;
            }
        }
        System.out.println(tmp);

        String[] w = tmp.split(" ");
        for(int k=0; k<w.length; k++){
            System.out.print((char)Integer.parseInt(w[k], 2));
        }



    }

    public int[][] getImage() {
        return image;
    }

}