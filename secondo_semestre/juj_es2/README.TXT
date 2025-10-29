consegna:

Procedendo in modo analogo a quanto visto a lezione, ridefinisci opportunamente la classe RoundTable e scrivi un
programma che risolve il problema proposto. Il nuovo protocollo pubblico della classe è così specificato:
rt = new RoundTable(n) : RoundTable costruttore della disposizione iniziale con (int) n ≥ 2 cavalieri
rt.numberOfKnights() : int numero di cavalieri a tavola
rt.servingKnights() : IntSList coppia (lista di due elementi) di cavalieri che possono servire o servirsi
rt.serveNeighbour() : RoundTable disposizione risultante dopo aver servito il prossimo cavaliere, che esce
rt.passJug() : RoundTable disposizione risultante dopo aver passato la brocca
In particolare, realizza una versione “efficiente” delle procedure del protocollo utilizzando due liste di interi
(IntSList) come nell’esempio più raffinato discusso a lezione.