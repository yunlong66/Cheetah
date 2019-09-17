###寻找两个有序数组的中位数

```c
double find2max(double x, double y, double z){
    if((x >= y && y >= z) || (z >= y && y >= x))
        return y;
    else if((x >= z && z >= y) || (y >= z && z >= x))
        return z;
    else
        return x;
}

double find2max_a(double x, double y, double z){
     printf("x = %f y = %f z = %f", x, y, z);
    if(x >= z && y >= z){
       
        return (x + y)/2;
    } 
    else if(z >= y && x >= y){
        // printf("??????");
        return (z + x)/2;
    }
    else
        return (y + z)/2;
}

double find2min_a(double x, double y, double z){
    if((x <= y && y <= z) || (y <= x && x <= z))
        return (x + y)/2;
    else if((x <= z && z <= y) || (z <= x && z <= y))
        return (z + x)/2;
    else
        return (y + z)/2;
}

double min(double x, double y, double z){
     printf("x = %f y = %f z = %f", x, y, z);
    if(x >= z && y >= z)
        return z;
    else if(z >= y && x >= y)
        return y;
    else
        return x;
}

double findMedianSortedArrays(int* nums1, int nums1Size, int* nums2, int nums2Size){
    int m = 0;
    int n = 0;
    int s = 1;
    int k = nums1Size + nums2Size;
    if(nums1Size == 0){
        if(k%2 == 0){
            return ((double)nums2[k/2] + (double)nums2[k/2 -1])/2;
        }else{
            return (double)nums2[k/2];
        }
    }else if(nums2Size == 0){
        if(k%2 == 0){
            return ((double)nums1[k/2] + (double)nums1[k/2 -1])/2;
        }else{
            return (double)nums1[k/2];
        }
    }
    
    if(nums1[nums1Size - 1] < nums2[0]){
        
    }else if(nums2[nums2Size - 1] > nums1[0]){
        
    }
    
    //如果是总位数是偶数
    if(k%2 == 0){
        if(k == 2){
            return ((double)nums1[0] + (double)nums2[0])/2;
        }
        int l1 = k/2 - 1;
        printf("l1 = %d", l1);
        while(s != l1){
            if(m == nums1Size - 1){
                n++;
                s++;
                continue;
            }else if(n == nums2Size - 1){
                m++;
                s++;
                continue;
            }
            if(nums1[m] < nums2[n]){
                m++;
            }else{
                n++;
            }
            s++;
        }
        
        if(m == 0 || n == 0){
             if(m == nums1Size - 1 && n != nums2Size - 1){
                printf("wwwwww");
                 printf("m = %d n = %d k = %d", nums1[m] ,nums2[n], nums2[n+1]);
                 if(n + 2 <= nums2Size -1 &&nums2[n+2] < nums1[m]){
                    double t = find2max_a((double) nums2[n+1], (double) nums2[n], (double) nums2[n+2]);
                    printf("t = %f", t);
                    return t;
                 }
                double t = find2max_a((double) nums1[m], (double) nums2[n], (double) nums2[n+1]);
                 printf("t = %f", t);
                return t;
            }else if(n == nums2Size - 1 && m != nums1Size - 1){
                 if(m + 2 <= nums1Size -1 &&nums1[m+2] < nums2[n]){
                    double t = find2max_a((double) nums1[m+1], (double) nums1[m], (double) nums1[m+2]);
                    printf("t = %f", t);
                    return t;
                 }
                double t = find2max_a((double) nums1[m], (double) nums1[m+1], (double) nums2[n]);
                return t;
            }
            else if(n == nums2Size - 1 && m == nums1Size - 1){
                //return nums1[m] < nums2[n] ? nums1[m] : nums2[n];
                return nums1[m];
            }else{
                printf("hahahahahahha");
                printf("m = %d n = %d ", m ,n);
                if(nums1[m] < nums2[n])
                    m++;
                else
                    n++;
                //return ((double)nums1[m] + (double)nums2[n])/2;
            }
        }
        
        // double t = find2max((double) nums1[m], (double) nums2[n], (double) nums2[n+1]);
        // return t;
        
        int k1 = nums1[m] > nums2[n] ? nums1[m] : nums2[n];
        printf("k1 = %d", k1);
        printf("m = %d n = %d", m ,n);
        int k2;
        if(m == nums1Size - 1){
            if(k1 == nums1[m])
                k2 = nums2[n] < nums2[n + 1] ? nums2[n] : nums2[n +1];
            else
                k2 = nums1[m] < nums2[n + 1] ? nums1[m] : nums2[n +1];
        }else if(n == nums2Size - 1){
            if(k1 == nums1[m])
                k2 = nums2[n] < nums1[m + 1] ? nums2[n] : nums1[m +1];
            else
                k2 = nums1[m + 1] < nums1[m] ? nums1[m + 1] : nums1[m];
        }
        else{
            printf("ppppp");
            if(k1 == nums1[m]){
                k2 = nums2[n] < nums1[m + 1] ? nums2[n] : nums1[m +1];
                printf("k1 = %d k2 = %d", k1, k2);
            }
                
            else
                k2 = nums1[m] < nums2[n+1] ? nums1[m] : nums2[n+1];
        }
        return ((double)k1 + (double)k2)/2;
    }else{
        int l1 = k/2;
        while(s != l1){
            if(m == nums1Size - 1){
                n++;
                s++;
                continue;
            }else if(n == nums2Size - 1){
                m++;
                s++;
                continue;
            }
            if(nums1[m] < nums2[n]){
                m++;
            }else{
                n++;
            }
            s++;
        }
        
        if(m == 0 || n == 0){
            if(m == nums1Size - 1 && n != nums2Size - 1){
                double t = find2max((double) nums1[m], (double) nums2[n], (double) nums2[n+1]);
                return t;
            }else if(n == nums2Size - 1 && m != nums1Size - 1){
                double t = find2max((double) nums1[m], (double) nums1[m+1], (double) nums2[n]);
                return t;
            }
            else if(n == nums2Size - 1 && m == nums1Size - 1){
                return nums1[m] < nums2[n] ? nums1[m] : nums2[n];
            }else{
                // if(nums1[m] < nums2[n])
                //     m++;
                // else
                //     n++;
                if(nums1[m] > nums2[n]){
            if(nums2[n+1] < nums1[m])
                return nums2[n+1];
            else 
                return nums1[m];
        }else{
            if(nums1[m+1] < nums2[n])
                return nums1[m+1];
            else 
                return nums2[n];
        }
                
                
            }
        }
        
        int k1 = nums1[m] < nums2[n] ? nums1[m] : nums2[n];
        if(m == nums1Size - 1){
            if(k1 == nums1[m])
                k1 = nums2[n] < nums2[n + 1] ? nums2[n] : nums2[n +1];
            else
                k1 = nums1[m] < nums2[n + 1] ? nums1[m] : nums2[n +1];
        }else if(n == nums2Size - 1){
            if(k1 == nums1[m])
                k1 = nums2[n] < nums1[m + 1] ? nums2[n] : nums1[m +1];
            else
                k1 = nums1[m + 1] < nums1[m] ? nums1[m + 1] : nums1[m];
        }else{
            if(k1 == nums1[m]){
                k1 = nums2[n] < nums1[m + 1] ? nums2[n] : nums1[m +1];
            }   
            else
                k1 = nums1[m] < nums2[n+1] ? nums1[m] : nums2[n+1];
        }
        
         
         return (double)k1;
//         printf("test m = %d n = %d", m ,n);
//         double t;
        
        
    }
    
}
```

