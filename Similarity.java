import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Similarity {

    public static <T> double similarity(ArrayList<T> list1, ArrayList<T> list2) {

        int size = list1.size();
        HashMap<Integer, T> hmap2 = new HashMap<Integer, T>();
        HashMap<T, Integer> hmap1 = new HashMap<T, Integer>();
        int L2[]=new int[size];


        for (int i = 0; i < list1.size(); i++) {
            hmap2.put(i+1,list2.get(i) );
            hmap1.put(list1.get(i),i+1);

        }

        for(int x=0;x<size;x++){
            L2[x]=hmap1.get(hmap2.get(x+1));

        }
        int temp[]=new int[size];
        int sizeL2=L2.length;

        int swapcount=  mergeSort(L2, temp, sizeL2-1, 0);
        double totalswaps= (size*(size-1))/2;
        double notswapped=totalswaps-swapcount;
        double percent=notswapped/totalswaps;
        return percent;


    }
    //recursive method, sorts L2 and counts swaps
    private static int mergeSort(int L2[], int temp[], int right, int left) {

        int mid, swapcount = 0;
        if (right > left) {
            mid = (right + left) / 2;
            swapcount=mergeSort(L2, temp, mid, left);
            swapcount+=mergeSort(L2, temp, right,mid + 1);
            swapcount+=merge(L2, temp, left, right,mid + 1);
        }
        return swapcount;
    }
    //Merges 2 sorted arrays and returns swap count
    private static int merge(int L2[], int temp[], int left, int right, int mid)
    {
        int swapcount=0;
        int i = left;
        int j = mid;
        int k = left;
        while ((i<=mid-1)&&(j<=right)){
            if (L2[i]<=L2[j]){
                temp[k++]=L2[i++];
            }
            else{
                temp[k++]=L2[j++];
                swapcount=swapcount+ (mid - i);
            }
        }
        while (i<=mid-1){
            temp[k++]=L2[i++];
        }

        while (j<=right){
            temp[k++] = L2[j++];
        }

        for (i=left;i<=right;i++){
            L2[i]=temp[i];
        }

        return swapcount;
    }




    public static void main (String []args){
        ArrayList<String> L1 = new ArrayList<String>();
        L1.add("A");
        L1.add("D");
        L1.add("B");
        L1.add("G");
        L1.add("C");
        L1.add("F");
        L1.add("E");

        ArrayList<String> L2 = new ArrayList<String>();
        L2.add("B");
        L2.add("A");
        L2.add("D");
        L2.add("G");
        L2.add("E");
        L2.add("C");
        L2.add("F");

        System.out.println( similarity(L1, L2));


    }

}
