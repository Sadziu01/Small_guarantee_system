package pwr.bsadowski.tools;

import java.util.List;

public class CreateString {
    public static String getString(String allToFileTo, List<Reclamation> lShopProducent) {
        for (Reclamation reclamation : lShopProducent) {
            String toFileTo = reclamation.getReclamationId() + ";" + reclamation.getClientId() + ";" + reclamation.getItemId() + ";" +
                    reclamation.getStatus() + ";" + reclamation.getOpis() + ";" + reclamation.getProducentId() + ";" +  reclamation.getDateReclamationStart() + ";" +
                    reclamation.getDateReclamationToWorker() + ";" + reclamation.getDateReclamationToProducent() + ";" +
                    reclamation.getDateReclamationToTaken() + ";" + reclamation.getDateReclamationEnd() + "\n";
            allToFileTo = allToFileTo + toFileTo;

        }
        return allToFileTo;
    }
}
