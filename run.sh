while getopts "e:" flag; do
  case "${flag}" in
  e) env=${OPTARG} ;;
  esac
done

export CDK_ENVIRONMENT="$env"
cdk deploy --all --parameter environment="$env"