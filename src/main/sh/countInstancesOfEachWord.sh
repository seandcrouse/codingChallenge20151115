cat $1 | tr [:space:] '\n' | tr -c [:alnum:] '\n' | grep -v "^\s*$" | sort | uniq -c | sort -bnr