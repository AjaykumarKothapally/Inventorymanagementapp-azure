import hashlib
import hmac
import base64
from datetime import datetime

# Replace with your actual values
master_key = "tHd5gEAZOyGtwhygEr2agd0UsR2f0u24cgfWcRO7z2JDwGCy1grSl3AawFyj0cFfIv1DFwRlZiyAACDbBAMy2Q=="
verb = "get"
resource_type = "dbs"
resource_link = ""
date = datetime.utcnow().strftime("%a, %d %b %Y %H:%M:%S GMT")

# Build the string to sign
string_to_sign = f"{verb}\n{resource_link}\n{resource_type}\n{date}\n\n"

# Decode master key
key = base64.b64decode(master_key)

# Compute HMAC SHA256
signature = hmac.new(key, string_to_sign.encode("utf-8"), hashlib.sha256).digest()

# Encode the signature in Base64
encoded_signature = base64.b64encode(signature).decode("utf-8")

# Create Authorization header
auth_header = f"type=master&ver=1.0&sig={encoded_signature}"

print(f"x-ms-date: {date}")
print(f"Authorization: {auth_header}")
