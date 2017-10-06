package me.ienze.caEvolve.fitness;

/*

import me.ienze.caEvolve.Board;
import me.ienze.caEvolve.CA;
import me.ienze.caEvolve.CaEvolveSettings;
import me.ienze.caEvolve.FitnessCalculator;
import org.opencv.core.*;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.highgui.Highgui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ienze
 *-/
public class ImageSimilarityFitnessCalculator implements FitnessCalculator {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    private final FeatureDetector featureDetector;
    private final DescriptorExtractor descriptorExtractor;

    private final MatOfKeyPoint sourceImageDescriptors;

    public ImageSimilarityFitnessCalculator(String sourceImagePath) {
        featureDetector = FeatureDetector.create(FeatureDetector.SURF);
        descriptorExtractor = DescriptorExtractor.create(DescriptorExtractor.SURF);

        Mat sourceImage = Highgui.imread(sourceImagePath, Highgui.CV_LOAD_IMAGE_COLOR);

        MatOfKeyPoint sourceImageKeyPoints = new MatOfKeyPoint();
        featureDetector.detect(sourceImage, sourceImageKeyPoints);

        sourceImageDescriptors = new MatOfKeyPoint();
        descriptorExtractor.compute(sourceImage, sourceImageKeyPoints, sourceImageDescriptors);

    }

    @Override
    public double[] calculateFitnesses(CA[] cas) {

        double[] fitnesses = new double[cas.length];

        for (int i = 0; i < cas.length; i++) {

            CA ca = cas[i];

            Mat image = imageToMat(ca.getPreviewImage());


            MatOfKeyPoint imageKeyPoints = new MatOfKeyPoint();
            MatOfKeyPoint imageDescriptors = new MatOfKeyPoint();
            featureDetector.detect(image, imageKeyPoints);
            descriptorExtractor.compute(image, imageKeyPoints, imageDescriptors);

            List<MatOfDMatch> matches = new LinkedList<MatOfDMatch>();
            DescriptorMatcher descriptorMatcher = DescriptorMatcher.create(DescriptorMatcher.FLANNBASED);
            descriptorMatcher.knnMatch(imageDescriptors, sourceImageDescriptors, matches, 2);


            double fitness = matches.size();
            fitnesses[i] = fitness;
        }

        return fitnesses;
    }

    private Mat imageToMat(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        Mat newMat = new Mat(width, height, CvType.CV_8UC1);

        for(int r=0; r<width; r++){
            for(int c=0; c<height; c++){
                newMat.put(r, c, image.getRGB(r, c));
            }
        }

        return newMat;
    }

}

*/