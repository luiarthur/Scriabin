import numpy as np
import matplotlib
import matplotlib.pyplot as plt
from sklearn import cluster

from skimage.filters import threshold_otsu, threshold_adaptive
from skimage import data
import skimage

import scipy
#from skimage.filters import try_all_threshold

image = skimage.io.imread("../src/test/resources/img/sample.jpg")
#image = data.page()

def rgb2gray(rgb):
    r, g, b = rgb[:,:,0], rgb[:,:,1], rgb[:,:,2]
    gray = 0.2989 * r + 0.5870 * g + 0.1140 * b
    return gray

image = rgb2gray(image)

global_thresh = threshold_otsu(image)
binary_global = image > global_thresh

block_size = 35
binary_adaptive = threshold_adaptive(image, block_size, offset=10)

fig, axes = plt.subplots(nrows=3, figsize=(7, 8))
ax = axes.ravel()
plt.gray()

ax[0].imshow(image)
ax[0].set_title('Original')

ax[1].imshow(binary_global)
ax[1].set_title('Global thresholding')

ax[2].imshow(binary_adaptive)
ax[2].set_title('Adaptive thresholding')

for a in ax:
    a.axis('off')

plt.show()

### Some Edge Detector ###
Faler=np.array([ [[-1,0,1],[-1,0,1],[-1,0,1]], 
                 [[1,1,1],[0,0,0],[-1,-1,-1]],
                 [[-1,-1,-1],[-1,8,-1],[-1,-1,-1]],
                 [[0,1,0],[-1,0,1],[0,-1,0]] ])

### Vertical Edge Detection ###
vert_mask = np.array([[-1,0,1],
                      [-1,0,1],
                      [-1,0,1]])
### Horizontal Edge Detection ###
hor_mask = vert_mask.T
v = scipy.signal.convolve2d(image, vert_mask, boundary='symm', mode='same')
b = scipy.signal.convolve2d(image, hor_mask)
plt.imshow(b)
plt.show()

plt.imshow(v)
plt.show()

plt.imshow(image)
plt.show()


### Arthur ###
face = skimage.io.imread("img/fb_profile.jpg")

face_grey = rgb2gray(face)
plt.imshow(face_grey)
#plt.imshow(face)
plt.show()

### Some Edge Face ###
face_f = scipy.signal.convolve2d(face_grey, Faler[3,:,:])
plt.imshow(face_f)
plt.show()

### Vertical Edge Face ###
face_v = scipy.signal.convolve2d(face_grey, vert_mask)
plt.imshow(face_v)
plt.show()

### Horizontal Edge Face ###
face_h = scipy.signal.convolve2d(face_grey, hor_mask)
plt.imshow(face_h)
plt.show()

### Kmeans ###
face_mat = np.reshape(face, (np.prod(face.shape[:2]), face.shape[-1]))
km = cluster.KMeans(n_clusters=4).fit(face_mat)
face_clus = np.uint8(np.reshape(km.cluster_centers_[km.labels_,:], face.shape))

plt.imshow(face_clus)
plt.show()
