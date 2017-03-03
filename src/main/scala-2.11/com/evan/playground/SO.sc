/*
    Returns Seq of tuples with the following entries
    posTags: String = string of - delimited pos tags for this ngram
    depTags: String = string of - delimited dependency tags for this ngram
    relPos: Double[0,1] = relative position of first element of the ngra,
    numWords: Int = length of the ngram
    isTitle: Boolean = indicates if this
    isTag: Boolean = target classification value, inidcates if this ngram is a tag

*/
def makeFeatures(n: Int)(title: String, content: String, tags: List[String]): () =
{

    ()
}

def isPunc(s: String): Boolean =
{
    false
}