import objectdraw.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.event.*;

//  A program to illustrate the use of matrices by implementing some simple
//  transformations on the pixels of a digital image
public class ImageEdit extends WindowController implements ChangeListener, ActionListener {

        // Space to leave between the original and transformed images
        private static final double SPACER = 5;
        
        // range for JSlider
        private static final int ADJUSTMENTRANGE = 200;
        
        // maximum range of pixels used for blur
        private static final int MAXBLURRANGE = 5;
        
        private static final double MAXBRIGHTNESS = Math.sqrt(3*255*255);
        
        // the "current" blur range (to avoid time consuming recomputation)
        private int oldRange;
        
        // the original image
        private Image source;
        
        // the original and transformed views of the image
        private VisibleImage orig, preview;
        
        // the scaling factor used when displaying images
        private double origScale;
        
        // width and height of original in pixels
        private double origWidth, origHeight;
        
        // the color values for the original pixels
        Color sourcePixels[][];
        
        // the GUIcontrols
        private JSlider adjustment;
        private JComboBox optionSelection;

        
        
        
        public void begin() {
                
                // create the transformation selection menu
                optionSelection = new JComboBox();
                optionSelection.addItem("Convert to grayscale");
                optionSelection.addItem("Vertical inversion");
                optionSelection.addItem("Horizontal reversal");
                optionSelection.addItem("Rectangular averaging");
                optionSelection.addItem("Rotate");
                getContentPane().add(optionSelection, BorderLayout.SOUTH);
                optionSelection.addActionListener(this);
                
                // create the slider
                adjustment = new JSlider(SwingConstants.VERTICAL,0,ADJUSTMENTRANGE,ADJUSTMENTRANGE);
                getContentPane().add( adjustment,BorderLayout.EAST);
                adjustment.addChangeListener(this);
                
                validate();
                
                // get an image
                source = getImage("chapelhopkins.gif");
                
                // get its pixels
                sourcePixels = getPixelMap(source);
                
                // display image scaled to fit on display
                orig = new VisibleImage(source, 0, 0, canvas);
                
                origScale = getScaleFactor( source );           
                orig.setWidth(origWidth*origScale);
                orig.setHeight(origHeight*origScale);
                
                updatePreview();
                
        }

        
        
        
        // convert all the pixels in an image into grayscale 
        private void adjustSaturation() {
                
                Color transformed[][] = new Color[ (int) origWidth][(int) origHeight];
                
                for (int col = 0; col < transformed.length; col++ ) {
                        for (int row = 0; row < transformed[col].length; row++ ) {
                                
                                transformed[col][row] = 
                                        grayscalePixel( sourcePixels[col][row] );
                        }
                }
                
                displayPreview( transformed );
                
        }
        
        
        
        
        // convert a single pixel into its grayscale equivalent
        private  Color grayscalePixel(Color c ) {
                int red = c.getRed();
                int green = c.getGreen();
                int blue = c.getBlue();
                
                int brightness = (int) ( 255*Math.sqrt(red*red + green*green + blue*blue)/
                                                         MAXBRIGHTNESS
                                                       );
                
                return new Color( brightness , brightness, brightness, c.getAlpha());
        }
        
        
        
        
        // flip an image vertically
        private void invert() {
                
                Color transformed[][] = new Color[ (int) origWidth][(int) origHeight];
                
                for (int col = 0; col < transformed.length; col++ ) {
                        for (int row = 0; row < transformed[0].length; row++ ) {
                                transformed[col][row] = sourcePixels[col][transformed[col].length - row - 1];
                        }
                }
                
                displayPreview( transformed );
        }
        
        
        // flip an image horizontally
        private void flip() {
                
                Color transformed[][] = new Color[ (int) origWidth][(int) origHeight];
                
                for (int col = 0; col < transformed.length; col++ ) {
                        transformed[col] = sourcePixels[transformed.length - col - 1];
                }
                
                displayPreview( transformed );
        }
        
        
        // blur an image by replacing each pixel with the average of the color values
        // of its neighbors in a 2*range+1 by 2*range+1 square.
        private void blur(int range) {
                
                Color transformed[][] = new Color[ (int) origWidth][(int) origHeight];
                
                for (int col = 0; col < transformed.length; col++ ) {
                        for (int row = 0; row < transformed[col].length; row++ ) {
                                
                                int counted = 0;
                                double redSum, greenSum, blueSum;
                                redSum = greenSum = blueSum = 0;

                                for ( int nearbyCol = Math.max(0,col-range); 
                                      nearbyCol < Math.min( transformed.length, col+range+1 ); 
                                      nearbyCol++) {
                                        
                                        for ( int rearbyRow = Math.max(0,row-range); 
                                              rearbyRow < Math.min( transformed[nearbyCol].length, row+range+1 ); 
                                              rearbyRow++) {
                                                
                                                redSum += sourcePixels[nearbyCol][rearbyRow].getRed();
                                                blueSum += sourcePixels[nearbyCol][rearbyRow].getBlue();
                                                greenSum += sourcePixels[nearbyCol][rearbyRow].getGreen();
                                                counted++;
                                        }
                                }
                                
                                transformed[col][row] = new Color( (int) (Math.round( redSum/counted)), 
                                                                           (int) (Math.round( greenSum/counted)),
                                                                           (int) (Math.round( blueSum/counted)),
                                                                                               sourcePixels[col][row].getAlpha()
                                                                                             );
                        }
                }
                
                displayPreview( transformed );
        }
        
        
        // rotate the image
        private void rotate() {
                // angle of rotation
                double theta = 2*Math.PI*(1.0*adjustment.getValue()/ADJUSTMENTRANGE);
                        
                // work in square based on maximum image dimension
                int maxDim = (int) Math.max(origWidth,origHeight);
                Color transformed[][] = new Color[maxDim][maxDim];
                
                // determine center about which to rotate       
                double origCenterX = origWidth/2.0;
                double origCenterY = origHeight/2.0;
                double transCenter = maxDim/2;

                for (int row = 0; row < maxDim; row++ ) {
                        for (int col = 0; col < maxDim; col++ ) {
                                
                                // find pixel position (r,c) in original corresponding to (i,j) in rotated image 
                                double transDx = (row-transCenter);
                                double transDy = (col-transCenter);
                                int r =  (int) Math.round(transCenter+(transDx*Math.cos(theta)-transDy*Math.sin(theta)));
                                int c =  (int) Math.round(transCenter+(transDx*Math.sin(theta)+transDy*Math.cos(theta)));
                                
                                // extract desired pixel if in range
                                if ( r < sourcePixels.length && c < sourcePixels[row].length && r >= 0 && c >= 0 ) {
                                        transformed[row][col] = sourcePixels[r][c];
                                } else {
                                        transformed[row][col] = Color.white;
                                }
                        }
                }
                
                
                displayPreview( transformed );
                
        }
        
        
        // update the preview of the transformed image
        private void displayPreview( Color transformed[][] ){
                Image stillPic = makeImage(transformed);
                if ( preview != null ) {
                        preview.removeFromCanvas();
                }
                preview = new VisibleImage( stillPic, orig.getWidth()+5,0, canvas);
                preview.setWidth(origScale*preview.getWidth());
                preview.setHeight(origScale*preview.getHeight());
                
        }
        
        
        // update the transformed image depending on menu selection
        private void updatePreview() {
                Object option = optionSelection.getSelectedItem();
                
                if ( option.equals("Convert to grayscale")) {
                        adjustSaturation();
                }
                else if ( option.equals("Vertical inversion")) {
                        invert();
                }
                else if ( option.equals("Horizontal reversal")) {
                        flip();
                }
                else if ( option.equals("Rectangular averaging")) {
                        int range = (int)  Math.round(MAXBLURRANGE *(adjustment.getValue()+0.0)/(ADJUSTMENTRANGE));
                        
                        if ( range != oldRange ) {
                                blur(range);
                                oldRange = range;
                        }
                }
                else if ( option.equals("Rotate")) {
                        rotate();
                }
                
        }
        
        
        
        // update when the slider moves
        public void stateChanged(ChangeEvent e){
                updatePreview();
        }
        
        
        
        // update when a menu item is selected (and reset the slider)
        public void actionPerformed(ActionEvent e){
                oldRange = MAXBLURRANGE + 1;
                adjustment.setValue(0);
                updatePreview();
        }

        
        // determine how much to scale image to fit two copies in display
        private double getScaleFactor( Image source) {
                double horizScale, vertScale;           
                
                origWidth = source.getWidth(null);
                origHeight = source.getHeight(null);
                
                if ( origWidth < (canvas.getWidth()-SPACER)/2 ){
                        horizScale = 1;
                } else {
                        horizScale = (canvas.getWidth()-SPACER)/2/origWidth;
                }
                if ( origHeight < canvas.getHeight() ){
                        vertScale = 1;
                } else {
                        vertScale = canvas.getHeight()/origHeight;
                }
                return  Math.min(horizScale,vertScale);
        }
        
        
        // convert an integer color value into an object of the Color class
        public Color int2Color(int pixel) {
                int alpha = (pixel >> 24) & 0xff;
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                return new Color(red, green, blue, alpha);
        }

        // get the 2D array of Colors that represent an Image
        public Color[][] getPixelMap(Image img) {
                
                // force Java to load the image
                MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(img, 0);
                try {
                        mediaTracker.waitForID(0);
                } catch (InterruptedException ie) {
                        System.out.println(ie);
                }
                
                // get a 1D array of the pixels
                int width = img.getWidth(null);
                int height = img.getHeight(null);
                int[] pixels = new int[width * height];
                PixelGrabber pg = new PixelGrabber(img, 0, 0, width, height, pixels, 0, width);
                try {
                        pg.grabPixels();
                } catch (InterruptedException e) {
                        System.err.println("interrupted waiting for pixels!");
                }
                if ((pg.getStatus() & ImageObserver.ABORT) != 0) {
                        System.err.println("image fetch aborted or errored");
                }
                
                // regroup the pixels into a 2D array
                Color result[][] = new Color[width][height];
                for (int col = 0; col < height; col++) {
                        for (int row = 0; row < width; row++) {
                                result[row][col] = int2Color(pixels[col * width + row]);
                        }
                }
                return result;
        }
        
        // Convert a 2D array of Colors into an Image
        public Image makeImage(Color[][] pixelMap) {
                int width = pixelMap.length;
                int height = pixelMap[0].length;
                
                // place the pixel values in an appropriate 1D array
                int pix[] = new int[width * height];
                int index = 0;
                for (int col = 0; col < height; col++) {
                        for (int row = 0; row < width; row++) {
                                int red = pixelMap[row][col].getRed();
                                int green = pixelMap[row][col].getGreen();
                                int blue = pixelMap[row][col].getBlue();
                                int alpha = pixelMap[row][col].getAlpha();
                                    pix[index++] =
                                                (alpha << 24) | (red << 16) | (green << 8) | blue;
                        }
                }
                
                // create an image from the 1D array
                return createImage(new MemoryImageSource(width, height, pix, 0, width));
        }
}
