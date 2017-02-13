from skimage import data
from skimage.transform import resize
from skimage.io import imread
from skimage.io import imshow
import matplotlib.pyplot as plt

img = imread("../../src/test/resources/img/sample.jpg")
scale = .05
smaller_img = resize(img, (img.shape[0]*scale, img.shape[1]*scale), mode='reflect')
imshow(smaller_img)
plt.show()

# use this as reference: http://www.pyimagesearch.com/2015/03/16/image-pyramids-with-python-and-opencv/
