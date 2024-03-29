
package libsys;
import java.util.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import java.io.*;



public class ImageInsert implements DropTargetListener{
    public String ImagePath;
     @Override
    public void drop(DropTargetDropEvent event) {
        
        // Accept copy drops
        event.acceptDrop(DnDConstants.ACTION_COPY);

        // Get the transfer which can provide the dropped item data
        Transferable transferable = event.getTransferable();

        // Get the data formats of the dropped item
        DataFlavor[] flavors = transferable.getTransferDataFlavors();
        

        // Loop through the flavors
        for (DataFlavor flavor : flavors) {
            try {
                
                // If the drop items are files
                if (flavor.isFlavorJavaFileListType()) {
                    // Get all of the dropped files
                    List<File> files = (List) transferable.getTransferData(flavor);
                    
                    // Loop them through
                    for (File file : files) {
                        ImagePath=file.getPath();
                    }
                }

            } catch (Exception e) {

                // Print out the error stack
                e.printStackTrace();

            }
        }

        // Inform that the drop is complete
        event.dropComplete(true);

    }
    

    @Override
    public void dragEnter(DropTargetDragEvent event) {
    }

    @Override
    public void dragExit(DropTargetEvent event) {
    }

    @Override
    public void dragOver(DropTargetDragEvent event) {
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent event) {
    }
}
