public class oefen {
    public static void main(String[] args) {
        for (int i = 2; i<4; i++){
        System.out.println(random(1,4));
        }
    }

    private static int random(int min, int max){
        int random = (int) ((Math.random() * ((max - min) + 1)) + min);
        return random;
    }
}
