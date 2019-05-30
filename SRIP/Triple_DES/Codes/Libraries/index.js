var store_cipher = "00000110 01100100 10110111 11110101 10001001 11100010 10010101 0110100";

function Encrypt(key) {
    console.log("text->>" + store_cipher);
    console.log("Key->" + key);
    var ciphertext = CryptoJS.DES.encrypt(store_cipher.toString(), key.toString());
    console.log("Ciphered text->>" + ciphertext.toString());
    store_cipher = ciphertext.toString();
}
function Decrypt(key,status) {
    console.log("text->>" + store_cipher);
    console.log("Key->" + key);
    var ciphertext = CryptoJS.DES.decrypt(store_cipher, key);
    console.log("Un-Ciphered text->>" + ciphertext.toString());
    store_cipher = ciphertext.toString();
    if(status===1)
    {
        document.getElementById("output").value=ciphertext.toString(CryptoJS.enc.Utf8);
    }

}
// function Encrypt() {

//     // Encrypt
//     var ciphertext = CryptoJS.DES.encrypt('my message', 'secret key 123');

//     // Decrypt
//     var bytes = CryptoJS.DES.decrypt(ciphertext.toString(), 'secret key 123');
//     var plaintext = bytes.toString(CryptoJS.enc.Utf8);
//     console.log("Real text->>"+plaintext);
// }
function Encryption() {
    Encrypt("3b3898371520f75e");
    Decrypt("922fb510c71f436e",0);
    Encrypt("3b3898371520f75e");
}
function Decryption() {
    Decrypt("3b3898371520f75e",0);
    Encrypt("922fb510c71f436e");
    Decrypt("3b3898371520f75e",1);
}