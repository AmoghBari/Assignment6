# Use a base image that includes Python and kubectl
FROM python:3.8-slim


# Install kubectl dependencies
RUN apt-get update && apt-get install -y curl
RUN pip install psutil
RUN apt-get install -y git

# Install kubectl
RUN curl -LO https://dl.k8s.io/release/v1.22.0/bin/linux/amd64/kubectl && \
    install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl


RUN rm -rf /app/*

WORKDIR /app

# Copy the Python script into the container
RUN git clone https://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@github.com/AmoghBari/Assignment6.git /app

EXPOSE 8080

# Run the Python script when the container launches
CMD ["python", "monitor.py", "sh", "-c"]
