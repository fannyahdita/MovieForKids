#include "jni.h"
#include "stdlib.h"

JNIEXPORT jint JNICALL Java_id_ac_ui_cs_mobileprogramming_fannyah_1dita_movieforkids_view_fragment_MovieListFragment_randommov
  (JNIEnv * env, jobject obj) {
    int n;
    n = rand()%6;

    return n;
}

