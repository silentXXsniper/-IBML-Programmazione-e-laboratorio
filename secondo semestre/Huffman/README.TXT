per informazioni consultare il pdf nella medesima cartella

per far girare il programma:


per eseguire il programma in un unica volta:
Huffman.compress("Huffman.java","C.txt")





le vecchie versioni funzinoavano con (ora non più):

Huffman.charFreq("Huffman.java") //o qual si voglia file di input - per convenzione lo si salva come freq
                                 //ATTENZIONE: per qualche motivo a volte non è possibile visualizzare il contenuto del <object reference>, non vi preoccupate lui funziona lo stesso
Huffman.huffmanTree(freq)  //nome_del_elemento_sopra - per convenzione viene salvato come tree
                          
Huffman.huffmanTable(tree) //per convenzione viene salvato come tab

Huffman.compress(tab,"Huffman.java","C.txt"); //il file selezionato viene compresso per stregonerie oscure (guarda pdf per capire come fa)

Huffman.decompress(tree,"C.txt","D.txt"); //fa tornare il file come era prima decomprimendolo
