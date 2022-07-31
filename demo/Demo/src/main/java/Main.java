import com.beastwall.storagemanager.FileSaver;
import com.beastwall.storagemanager.callback.FileSavedCall;
import com.beastwall.storagemanager.callback.ProgressCallback;
import com.beastwall.storagemanager.utils.MimeType;
import com.beastwall.storagemanager.utils.StorageUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author AbdelWadoud Rasmi
 * <p>
 * Main entry point for tis java program
 */
public class Main {


    public static void main(String[] args) throws FileNotFoundException {
        /**
         * creating some demo inputs from our files, to demonstrate the library features
         */
        String pdfUrl = FileSaver.get().getClass().getClassLoader().getResource("pdf/StorageManager-lib.pdf").getFile();
        String imgUrl = FileSaver.get().getClass().getClassLoader().getResource("image/beastwall-name.png").getFile();
        String txtUrl = FileSaver.get().getClass().getClassLoader().getResource("others/StorageManager-lib.txt").getFile();
        String zipUrl = FileSaver.get().getClass().getClassLoader().getResource("others/StorageManager-lib.zip").getFile();
        String wordUrl = FileSaver.get().getClass().getClassLoader().getResource("word-powerpoint/StorageManager-lib.docx").getFile();


        /**
         * Construct a FileSaver object
         */
        FileSaver saver = FileSaver.get();

        /**
         * Save a file
         */
        File pdfFile = new File(pdfUrl);
        String pdf = saver.save(pdfFile, "pdffile.pdf");

        /**
         * Save an inputstream
         */
        InputStream inputStream = new FileInputStream(imgUrl);
        String png = saver.save(inputStream, "image.png");

        /**
         * Copy a file using its path
         */
        String zip = saver.save(zipUrl, "file.zip");

        /**
         * Save a file asynchronously in a separate thread (meant for performance)
         */
        saver.saveAsync(zipUrl, "fileAsync.zip", new FileSavedCall() {
            @Override
            public void result(boolean success, String file) {
                String zip = file;
            }
        });

        /**
         * Copy a file into a specefic directory
         */
        String text = saver.save(new File(txtUrl), "StorageManager/demo/Demo/newdir", "file.txt");


        /**
         * Progress call back
         */
        String path = saver.setProgressCallBack(new ProgressCallback() {
            @Override
            public void progress(long totalBytes, long numberOfReadBytes, int percentage) {
                System.out.println(percentage);
            }
        }).save(new File(txtUrl), "StorageManager/demo/Demo/newdir", "file1.txt");

        /**
         * changing default file length
         */
        File textFile = new File(txtUrl);
        String text2 =
                saver.setInputLength(textFile.length())
                        .save(textFile, "StorageManager/demo/Demo/newdir", "file2.txt");


        /**
         * Random file name with estension using mimeType
         */
        String fileName = StorageUtils.randomNameWithExtension("any-prefix", MimeType.APPLICATION_JSON);
        System.out.println(fileName);

        /**
         * short expression
         */
        textFile = new File(txtUrl);
        FileSaver
                .get()
                .setProgressCallBack((totalBytes, numberOfReadBytes, percentage) -> {})
                .setInputLength(textFile.length())
                .save(textFile, "StorageManager/demo/Demo/newdir", "file2.txt");
    }


}
